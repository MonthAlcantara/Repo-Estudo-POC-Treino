package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import io.github.monthalcantara.bibliotecaCDD.usuario.UsuarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/emprestimos")
public class PedidoEmprestimoController {

    private SolicitacaoEmprestimoService solicitacaoEmprestimoService;

    public PedidoEmprestimoController(SolicitacaoEmprestimoService solicitacaoEmprestimoService) {
        this.solicitacaoEmprestimoService = solicitacaoEmprestimoService;
    }

    @PostMapping
    public ResponseEntity solicitaEmprestimo(@Valid @RequestBody PedidoEmprestimo pedidoEmprestimo) {

        UsuarioResponse usuarioResponse = solicitacaoEmprestimoService.solicitaEmprestimo(pedidoEmprestimo.getIdUsuario(), pedidoEmprestimo.getLivros());

        return ResponseEntity.ok(usuarioResponse);
    }
}
