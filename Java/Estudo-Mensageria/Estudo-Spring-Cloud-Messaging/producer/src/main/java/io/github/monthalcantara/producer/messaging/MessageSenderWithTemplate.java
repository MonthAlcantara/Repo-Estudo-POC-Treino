package io.github.monthalcantara.producer.messaging;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.github.monthalcantara.producer.core.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderWithTemplate implements MessageSender {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${aws.queue.name}")
    private String queueAwsName;

    /*
        As mensagens são criadas usando a classe auxiliar do MessageBuilder.
        O MessageBuilder fornece dois métodos de fábrica para criar mensagens de uma mensagem
        existente ou com um objeto Payload.
      */
    @Override
    public void send(String messagePayload) {
        Message<String> msg = MessageBuilder.withPayload(messagePayload)
                .setHeader("TransactionId", "123")
                .setHeaderIfAbsent("country", "BR")
                .build();

        queueMessagingTemplate.convertAndSend(queueAwsName, msg);
    }
}
