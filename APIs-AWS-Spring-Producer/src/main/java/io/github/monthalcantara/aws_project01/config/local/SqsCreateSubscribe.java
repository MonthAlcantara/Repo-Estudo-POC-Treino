package io.github.monthalcantara.aws_project01.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.util.Topics;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("local")
public class SqsCreateSubscribe {

    //Criação do Client para comunicação com SQS
    public SqsCreateSubscribe(AmazonSNS snsClient, @Qualifier("productEventsTopic") Topic productEventsTopic) {
       //Mesma ideia da config do SNS
        AmazonSQS sqsClient = AmazonSQSClient.builder()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",
                                Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        //Criando a fila product-events e pegando a url
        String productEventsQueueUrl = sqsClient.createQueue(
                new CreateQueueRequest("product-events")).getQueueUrl();

        //Escrevendo a fila no tópico
        Topics.subscribeQueue(snsClient, sqsClient, productEventsTopic.getTopicArn(), productEventsQueueUrl);
    }
}
