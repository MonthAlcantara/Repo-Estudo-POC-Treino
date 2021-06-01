package io.github.monthalcantara.configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SqsCreate {

    //Criação do Client para comunicação com SQS
    public SqsCreate(AmazonSQS sqsClient) {

        AmazonSQS amazonSqsClient = AmazonSQSClient.builder()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4576",
                                Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        //Criando a fila fila_teste e pegando a url
        String productEventsQueueUrl = sqsClient.createQueue(
                new CreateQueueRequest("fila_teste")).getQueueUrl();

    }
}
