package io.github.monthalcantara.producer;

import com.amazonaws.services.sqs.AmazonSQS;
import io.github.monthalcantara.core.messaging.SqsQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

//@Service
public class SqsQueueSenderImpl implements SqsQueueSender {

    @Value("${aws.fila.name}")
    private String queue;

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;


    public void send(String msg) {
        queueMessagingTemplate.convertAndSend(queue, msg);
    }
}
