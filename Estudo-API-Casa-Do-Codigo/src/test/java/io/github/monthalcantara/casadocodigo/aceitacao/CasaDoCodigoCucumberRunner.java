package io.github.monthalcantara.casadocodigo.aceitacao;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.monthalcantara.casadocodigo.CasadocodigoApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {CasadocodigoApplication.class,
                           CasaDoCodigoCucumberRunner.class},
                            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@RunWith(Cucumber.class)
//Onde estão os arquivos features
@CucumberOptions(features = "classpath:features")
public class CasaDoCodigoCucumberRunner {
}