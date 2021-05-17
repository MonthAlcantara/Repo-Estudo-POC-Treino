package io.github.monthalcantara.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Numa aplicação modularizada é necessario no pom da raiz especificar os módulos e
na classe do application eu preciso fazer um scan nos pacotes que possuem determinado nome
*/
@SpringBootApplication(scanBasePackages = "io.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
