package com.myorg;

import software.amazon.awscdk.core.*;
import software.amazon.awscdk.services.ec2.*;
import software.amazon.awscdk.services.rds.*;

import java.util.Collections;

/*
 *   Qual tipo da maquina que rodará a instancia do banco e qual a estratégia?
 * Essa instancia estará dentro da VPC e só será acessada por quem estiver na VPC
 * */
public class RdsStack extends Stack {
    public RdsStack(final Construct scope, final String id, Vpc vpc) {
        this(scope, id, null, vpc);
    }

    public RdsStack(final Construct scope, final String id, final StackProps props, Vpc vpc) {
        super(scope, id, props);

        /*
         * Particularidade do CDK. COmo não vou setar a senha do banco hardfe code, e sim por meio de parametro,
         * com esse CfnParameter eu digo o tipo e a descrição do parametro
         * */
        CfnParameter databasePassword = CfnParameter.Builder.create(this, "databasePassword")
                .type("String")
                .description("The RDS instance password")
                .build();


        //Preciso liberar a porta que o banco vai rodar no security group
        ISecurityGroup iSecurityGroup = SecurityGroup.fromSecurityGroupId(this, id, vpc.getVpcDefaultSecurityGroup());
        //Qualquer IP dentro da VPC pode acessar o banco na porta 3306
        iSecurityGroup.addIngressRule(Peer.anyIpv4(), Port.tcp(3306));

        // Construção da Instancia de RDS
        DatabaseInstance databaseInstance = DatabaseInstance.Builder
                //Escopo e Identificador
                .create(this, "Rds01")
                //nome da Instancia
                .instanceIdentifier("aws-project01-db")
                //Tipo da Instancia
                .engine(DatabaseInstanceEngine.mysql(MySqlInstanceEngineProps.builder()
                        .version(MysqlEngineVersion.VER_5_6)
                        .build()))
                //VPC onde ela estará
                .vpc(vpc)
                //Mecanismo de acesso ao banco será senha e usuario
                .credentials(Credentials.fromUsername("admin",
                        CredentialsFromUsernameOptions.builder()
                                //Aqui eu uso justamente o parametro que eu vou passar por variável de ambiente, é o modo do CDK de fazer isso
                                .password(SecretValue.plainText(databasePassword.getValueAsString()))
                                .build()))
                //Definindo o tamanho da maquina
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO))
                //Não quero que a instancia fique em mais de uma zona
                .multiAz(false)
                //Tamanho do disco (10Gb)
                .allocatedStorage(10)
//                setando o security group
                .securityGroups(Collections.singletonList(iSecurityGroup))
                //Quais as subredes
                .vpcSubnets(SubnetSelection.builder()
                        .subnets(vpc.getPrivateSubnets())
                        .build())
                .build();

        //Exportar parametros para poder usar em outra Stack por exemplo o endpoint e a senha do RDS
//é a forma do CDK de fazer isso no CfnParametereu seto entrada e no CfnOutput eu seto saída
        //Esses valores serão usados na classe de Service01Stack, pois ele é quem vai acessar o banco
        CfnOutput.Builder.create(this, "rds-endpoint")
                .exportName("rds-endpoint")
                .value(databaseInstance.getDbInstanceEndpointAddress())
                .build();

        CfnOutput.Builder.create(this, "rds-password")
                .exportName("rds-password")
                .value(databasePassword.getValueAsString())
                .build();
/**
 * Nesse caso ao subir o CDK eu informo tambem os parametros, por exemplo
 * "cdk deploy --parameters Rds:databasePassword-algumasenha123 Stack01 Stack02 ..." e assim por diante
 * */
    }
}
