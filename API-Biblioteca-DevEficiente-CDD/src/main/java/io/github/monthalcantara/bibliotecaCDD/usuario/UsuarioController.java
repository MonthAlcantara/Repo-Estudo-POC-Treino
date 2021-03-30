package io.github.monthalcantara.bibliotecaCDD.usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity cadastraUsuario(@RequestBody @Valid NovoUsuarioRequest novoUsuarioRequest){
        logger.info("Recebida requisição para criação de novo usuario");

        Usuario usuario = novoUsuarioRequest.toModel();

        logger.info("Realizada conversão de Usuariorequest para  usuario");

        usuario = usuarioRepository.save(usuario);

        logger.info("Usuario salvo no banco de dados");

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuario);

        logger.info("Realizada conversão de Usuario salvo para  UsuarioResponse");

        return new ResponseEntity(usuarioResponse, HttpStatus.CREATED);
    }
}
