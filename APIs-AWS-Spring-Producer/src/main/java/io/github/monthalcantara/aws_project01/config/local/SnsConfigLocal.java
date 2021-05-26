package io.github.monthalcantara.aws_project01.config.local;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class SnsConfigLocal {

    private static final Logger logger = LoggerFactory.getLogger(SnsConfigLocal.class);

    //Arn (Url) do serviço, no caso SNS
    private final String productEventsTopic;
    private final AmazonSNS snsClient;

    @Bean
    public AmazonSNS snsClient() {
        return this.snsClient;
    }

    public SnsConfigLocal() {
        //O SDK da Amazon possui algumas formas de criar o cliente, essa é uma delas
        this.snsClient = AmazonSNSClient.builder()
                //Como estou usando o localStack, eu posso usar a configuração onde aponto o endpoint, a url do serviço, porém apontando pra o localstack
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(
                        "http://localhost:4566",
                        //Regiao definida no localStack
                        Regions.US_EAST_1.getName()))
                //Credenciais Default
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        //Aqui estou criando um tópico "na mão" e dando o mesmo nome do tópico usado em deve para não precisar alterar outras
//        partes do código
        CreateTopicRequest createTopicRequest = new CreateTopicRequest("product-event");
        this.productEventsTopic = this.snsClient.createTopic(createTopicRequest).getTopicArn();

        logger.info("SNS topic ARN: {}", this.productEventsTopic);
    }


    @Bean(name = "productEventsTopic")
    public Topic snsProductEventsTopic() {
        return new Topic().withTopicArn(productEventsTopic);
    }

}
