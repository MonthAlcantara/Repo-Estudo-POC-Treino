package io.github.monthalcantara.estudoaws.service

import io.awspring.cloud.messaging.core.NotificationMessagingTemplate
import io.github.monthalcantara.estudoaws.dto.PessoaRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class NotificaNovoCadastroService(
    val notificationMessagingTemplate: NotificationMessagingTemplate,
    @Value("\${aws.sns-topic}") val topicName: String
) {
    val log: Logger = LoggerFactory.getLogger(NotificaNovoCadastroService::class.java)

    fun notifica(request: PessoaRequest) {

        //Using convertAndSend method
        log.info("Method convertAndSend args $request. Using default topic.")

        notificationMessagingTemplate.convertAndSend(request)

        log.info("Method convertAndSend args $topicName and $request.")
    }
}
