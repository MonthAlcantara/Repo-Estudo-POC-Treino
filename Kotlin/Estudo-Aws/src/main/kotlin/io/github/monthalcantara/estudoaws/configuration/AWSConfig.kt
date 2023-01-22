package io.github.monthalcantara.estudoaws.configuration

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.AmazonSNSClientBuilder
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.MappingJackson2MessageConverter


@Configuration
data class AWSConfig(
    @Value("\${aws.region}")
    val awsRegion: String,
    @Value("\${aws.access-key}")
    val awsAccessKey: String,
    @Value("\${aws.secret-key}")
    val awsSecretKey: String,
    @Value("\${aws.sns-endpoint}")
    val snsEndpoint: String,
    @Value("\${aws.sns-topic}")
    val topicName: String
) {
    @Bean
    fun credentials() : AWSCredentials = BasicAWSCredentials(awsAccessKey, awsSecretKey)

    @Bean
    fun amazonSNS(): AmazonSNS {
        return AmazonSNSClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials()))
            .withEndpointConfiguration(
                EndpointConfiguration(snsEndpoint, awsRegion)
            )
            .build()
    }

    @Bean
    fun notificationMessagingTemplate(): NotificationMessagingTemplate {
        val notificationMessagingTemplate = NotificationMessagingTemplate(amazonSNS())
        val mappingJackson2MessageConverter = MappingJackson2MessageConverter()
        mappingJackson2MessageConverter.serializedPayloadClass = String::class.java
        mappingJackson2MessageConverter.objectMapper.registerModule(JavaTimeModule())
        notificationMessagingTemplate.messageConverter = mappingJackson2MessageConverter
        notificationMessagingTemplate.setDefaultDestinationName("purchase-transactions-topic")
        return notificationMessagingTemplate
    }
}