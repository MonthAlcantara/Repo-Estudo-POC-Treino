package io.github.monthalcantara.producer.controller;

import io.github.monthalcantara.producer.core.MessageSender;
import io.github.monthalcantara.producer.model.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/producer")
public class ControllerTest {

    @Autowired
    private MessageSender messageSender;

    @PostMapping
    public void publicaEvento(@RequestBody Mensagem mensagem) {
        messageSender.send(mensagem.toString());
    }
}
