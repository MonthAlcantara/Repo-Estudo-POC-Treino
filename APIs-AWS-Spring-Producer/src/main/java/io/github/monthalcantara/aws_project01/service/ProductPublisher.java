package io.github.monthalcantara.aws_project01.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.aws_project01.enums.EventType;
import io.github.monthalcantara.aws_project01.model.Envelope;
import io.github.monthalcantara.aws_project01.model.Product;
import io.github.monthalcantara.aws_project01.model.ProductEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductPublisher {

    private static final Logger logger = LoggerFactory.getLogger(ProductPublisher.class);
    private AmazonSNS snsClient;
    private Topic productEventsTopic;
    private ObjectMapper objectMapper;

    /*
    Não é o caso desse projeto de estudo mas eu poderia ter mais de um tópico por SNS
    então aqui eu preciso informar a qual tópico eu estou me referindo
            */
    public ProductPublisher(AmazonSNS snsClient,
                            //O nome de topico foi registrado na aplicação do CDK no momento de criação do SNS e do tópico
                            //Ena classe de configuração desse projeto
                            @Qualifier("productEventsTopic") Topic productEventsTopic,
                            //ObjetctMapper para fazer o parse do objeto Java para JSon
                            ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.productEventsTopic = productEventsTopic;
        this.objectMapper = objectMapper;
    }

    //Método que de fato faz o publish
    /*
    * É necessario saber o que será enviado, noa caso o Product
    * Qual foi o evento criar, atualizar, excluir
    * e quem foi que fez isso, o Username
    * */
    public void publishProductEvent(Product product, EventType eventType, String username) {
        //De posse desses dados eu crio o produto que será enviado e depois adiciono no envolope
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProductId(product.getId());
        productEvent.setCode(product.getCode());
        productEvent.setUsername(username);

        Envelope envelope = new Envelope();
        envelope.setEventType(eventType);

        try {
            //Como o envio no tópico é por meio de Json, o Object mapper transforma o objeto que vou enviar num Json
            //Que no java é uma String
            envelope.setData(objectMapper.writeValueAsString(productEvent));

            //Usando o cliente do sns no método publish passando o endpoint do sns na aws (arn) e o json do envelope
            snsClient.publish(
                    productEventsTopic.getTopicArn(),
                    objectMapper.writeValueAsString(envelope));

        } catch (JsonProcessingException e) {
            logger.error("Failed to create product event message");
        }
    }
}
