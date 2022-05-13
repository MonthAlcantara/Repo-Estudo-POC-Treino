package io.github.monthalcantara.aws_project02.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

//Acessar a fica e configurar as threads
@Configuration
@EnableJms //Essa anotação habilita o JMS na aplicação
@Profile("!local")
public class JmsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    private SQSConnectionFactory sqsConnectionFactory;

    //Esse Bean será usado pelo JMS
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        Fabrica de conexão com o SQS. Aqui além da região eu informo onde estão as credenciais para utilização do serviço
        sqsConnectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                        .withRegion(awsRegion)
                        .withCredentials(new DefaultAWSCredentialsProviderChain())
                        .build());

//        Tem como criar uma definição específica por fila mas da forma como está sendo feita aqui está se
//        adotando uma definição padrão para todas as filas da aplicação
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();

        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        //QUantas threads de consumo por fila
        factory.setConcurrency("2");
        //Quando eu receber a mensagem a mesma ja será indicada como recebida, conhecida
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }
}
