package io.github.monthalcantara.aws_project02.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.aws_project02.model.Envelope;
import io.github.monthalcantara.aws_project02.model.ProductEvent;
import io.github.monthalcantara.aws_project02.model.SnsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;

@Service
public class ProductEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(
            ProductEventConsumer.class);

    private ObjectMapper objectMapper;

    //Objeto para fazer a Deserialização do Json
    @Autowired
    public ProductEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    //Anotação informando qual tópico deve ser ouvido com o JMS e o objeto que ele vai receber que será colocaada num TextMessage
    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage)
    //Caso a mensagem esteja mal formada ou algo do tipo, essa exception é lançada
            throws JMSException, IOException {

        //Muito parecido com o q fazemos com o commandContext no pix. Pego o conteúdo mas informo na classe que quero convertelo e ele pega
//        o que for correspondente
        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(),
                SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(),
                Envelope.class);

     ProductEvent productEvent = objectMapper.readValue(
                envelope.getData(), ProductEvent.class);

        log.info("Product event received - Event: {} - ProductId: {} - MessageId: {}",
                envelope.getEventType(),
                productEvent.getProductId(),
                snsMessage.getMessageId());
    }
}
