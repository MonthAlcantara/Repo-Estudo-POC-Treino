package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
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



        solicitacaoEmprestimoService.solicitaEmprestimo(pedidoEmprestimo.getIdUsuario(), pedidoEmprestimo.getLivros());

        return ResponseEntity.ok(pedidoEmprestimo.getLivros().stream().map(LivroEmprestimoRequest::toString).collect(Collectors.toList()));
    }
}
