package io.github.monthalcantara.casadocodigo.configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = AutorRepository.class)
@Profile("local")
public class DynamoDbConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDbConfig.class);
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDbConfig() {

    this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", Regions.US_EAST_1.name()))
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();

        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB());

        List<AttributeDefinition> attributeDefinitions= new ArrayList<AttributeDefinition>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.S));

        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        CreateTableRequest request = new CreateTableRequest()
                .withTableName("Autores")
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions);

        Table table = dynamoDB.createTable(request);

        try {
            table.waitForActive();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return this.amazonDynamoDB;
    }

    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig(){
        return DynamoDBMapperConfig.DEFAULT;
    }

//    @Bean
//    @Primary
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
//                                         DynamoDBMapperConfig config){
//        return new DynamoDBMapper(amazonDynamoDB, config);
//    }
}
