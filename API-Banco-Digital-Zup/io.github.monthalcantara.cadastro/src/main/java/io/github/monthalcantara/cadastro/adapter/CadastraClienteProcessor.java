package io.github.monthalcantara.cadastro.adapter;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.github.monthalcantara.cadastro.visitor.AdicionaDataCriacaoVisitor;
import io.github.monthalcantara.core.command.Command;
import io.github.monthalcantara.core.command.CommandContext;
import io.github.monthalcantara.core.messaging.SqsQueueSender;
import io.github.monthalcantara.core.persistence.ClientePersistence;
import io.github.monthalcantara.domain.cliente.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastraClienteProcessor implements Command<Cliente> {

    @Autowired
    private AdicionaDataCriacaoVisitor adicionaDataCriacaoVisitor;

    @Autowired
    private ClientePersistence clientePersistence;

    @Autowired
    private AmazonSQSAsync amazonSQS;
//    @Autowired
//    private SqsQueueSender queueMessagingTemplate;

    @Override
    public Cliente process(CommandContext commandContext) {

        log.info("Iniciando processo de cadastro do cliente");

        Cliente cliente = (Cliente) commandContext.getData(commandContext);

        log.info("Recuperado valor do Command Context");

        cliente.accept(adicionaDataCriacaoVisitor);

        log.info("Finalizado processo de cadastro do cliente");

        new QueueMessagingTemplate(amazonSQS).convertAndSend("fila_teste", cliente);
       // queueMessagingTemplate.send(cliente.toString());
        //clientePersistence.save(cliente);

        return cliente;
    }
}
