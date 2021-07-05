package io.github.monthalcantara.casadocodigo.aceitacao.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.monthalcantara.casadocodigo.controller.CadastraAutorController;
import io.github.monthalcantara.casadocodigo.core.CadastraAutorCommandProcessor;
import io.github.monthalcantara.casadocodigo.dto.request.CadastraAutorRequest;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Cadastrando_autor_steps {

    private CadastraAutorRequest autor;
    private ResponseEntity cadastra;

    @Autowired
    private CadastraAutorController cadastraAutorController;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private CadastraAutorCommandProcessor cadastraAutorCommandProcessor;

    @Dado("um autor válido")
    public void um_autor_válido() {
        autor = new CadastraAutorRequest("Junior", "junior@gmail.com", "Autor desconhecido");
    }

    @Quando("solicito a criacao deste autor no banco")
    public void solicito_a_criacao_deste_autor_no_banco() {
        cadastra = cadastraAutorController.cadastra(autor);
    }

    @Entao("deve criar um autor no banco de dados")
    public void deve_criar_um_autor_no_banco_de_dados() {
        assertThat(autorRepository.findAll().isEmpty(), equalTo(1));
    }

}