package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.applicationautoscaling.EnableScalingProps;
import software.amazon.awscdk.services.ecs.*;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.elasticloadbalancingv2.HealthCheck;
import software.amazon.awscdk.services.events.targets.SnsTopic;
import software.amazon.awscdk.services.logs.LogGroup;

import java.util.HashMap;
import java.util.Map;

/*
 *   Aqui faz sentido colocar tudo que for referente ao mesmo assunto numa mesma stack
 * LoadBalance, Service...
 * */
public class Service01Stack extends Stack {

    public Service01Stack(final Construct scope, final String id, Cluster cluster, SnsTopic productEventTopic) {
        this(scope, id, null, cluster, productEventTopic);
    }

    public Service01Stack(final Construct scope, final String id, final StackProps props, Cluster cluster, SnsTopic productEventTopic) {
        super(scope, id, props);

        /**
         * Capturando as variaveis de ambiente para setar na aplicação concatenando com os valores informados no
         * stack do rds
         * Fn.importValue(Passando o nome do value) para importar os valores dessas variaveis
         */
        Map<String, String> envVariables = new HashMap<>();
        envVariables.put("SPRING_DATASOURCE_URL", "jdbc:mariadb://" + Fn.importValue("rds-endpoint")
                + ":3306/aws_project01?createDatabaseIfNotExist=true");
        envVariables.put("SPRING_DATASOURCE_USERNAME", "admin");
        envVariables.put("SPRING_DATASOURCE_PASSWORD", Fn.importValue("rds-password"));
        envVariables.put("AWS_REGION", "us-east-1");
        envVariables.put("AWS_SNS_TOPIC_PRODUCT_EVENT_ARN", productEventTopic.getTopic().getTopicArn());


        //LoadBalance do tipo fargate
        /*
         *  Como a aplicação rodará com Fargate, eu digo quanto de CPU, Memória e etc eu quero que minha
         *  aplicação rode e o fargate toma conta de tudo, sendo assim eu não preciso me preocupar com
         *  gerenciar instancias Ec2
         **/
        ApplicationLoadBalancedFargateService service01 = ApplicationLoadBalancedFargateService.Builder
                //Contexto + a Identificação do serviço LoadBalance
                .create(this, "ALB01")
                // Nome do Serviço
                .serviceName("service-01")
                // Nome do Cluster
                .cluster(cluster)
                //Quanto de CPU deve ser destinada a aplicação
                .cpu(512)
                //Memória que será utilizada na aplicação
                .memoryLimitMiB(1024)
                // Quantas instancias vão subir ao iniciar a aplicação
                .desiredCount(2)
                //Qual a porta que a plicação será executada
                .listenerPort(8080)
                //Definição de como a aplicação deverá ser executada
                .taskImageOptions(
                        ApplicationLoadBalancedTaskImageOptions.builder()
                                //Definindo um nome para o container
                                .containerName("aws_project01")
                                //Informando onde ele achará a imagem (nome registrado da imagem no dockerHub)
                                .image(ContainerImage.fromRegistry("montivaljunior/curso_aws_project01:1.2.0"))
                                //Porta do container
                                .containerPort(8080)
                                //Onde os logs da aplicação irão aparecer?
                                .logDriver(LogDriver.awsLogs(AwsLogDriverProps.builder()
                                        //Todos os logs estarão no cloud watch dento de logGroup chamado Service01LogGroup
                                        .logGroup(LogGroup.Builder.create(this, "Service01LogGroup")
                                                .logGroupName("Service01")
                                                //Ao remover essa stack os logs serão destruídos juntos (.DESTROY)
                                                .removalPolicy(RemovalPolicy.DESTROY)
                                                .build())
                                        //Dentro dos grupos de logs terão os strems para atualizar esse grupo
                                        .streamPrefix("Service01")
                                        .build()))
                                /**
                                * Aqui eu seto o hashmap com as variaveis de ambientes setadas
                                * */
                                .environment(envVariables)
                                .build())
                //Tornar o loadBalancer publico com um DNS para ser acessado de fora da VPC
                .publicLoadBalancer(true)
                .build();

        // Qual caminho que o load balance usará para saber a saúde da minha aplicação
        service01.getTargetGroup().configureHealthCheck(new HealthCheck.Builder()
                //Como adicionei a dependencia  do actuaator no projeto, eu informo o endpoint dele
                .path("/actuator/health")
                // A porta onde ele procurará
                .port("8080")
                // O Status code que indica que está ok
                .healthyHttpCodes("200")
                .build());

        //Definir a capacidade mínima e maxima do auto scaling
        ScalableTaskCount scalableTaskCount = service01.getService().autoScaleTaskCount(EnableScalingProps.builder()
                //Quero ter no mínimo duas instancias rodando
                .minCapacity(2)
                //Quero ter no máximo 4 instancias rodando
                .maxCapacity(4)
                .build());

        /*
         * Sob quais parametro eu quero que o meu autoscaling atue, ou seja quando ele vai levantar uma instancia e
         * quando ele vai matar alguma instancia respeitando o maximo e o minimo  informado no scalableTaskCount
         */
        scalableTaskCount.scaleOnCpuUtilization("Service01AutoScaling", CpuUtilizationScalingProps.builder()
                //O valor de monitoramento para é 50% de consumo da CPU
                .targetUtilizationPercent(50)
                //Se o consumo de CPU ultrapassar 50% durante 60 segundos, cria uma instancia
                .scaleInCooldown(Duration.seconds(60))
                //Se o consumo estiver abaixo ods 50% de CPU durante 60 seg, mata uma instancia
                .scaleOutCooldown(Duration.seconds(60))
                .build());
        //Importante essa definição para haver uma estratégia de lançamento de novas instancias e derrubadas

        //Pegando o tópico e setando entre os serviços que podem publicar nele o meu service01
        productEventTopic.getTopic().grantPublish(service01.getTaskDefinition().getTaskRole());
    }

}
