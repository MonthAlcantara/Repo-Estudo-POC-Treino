package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/emprestimos")
public class PedidoEmprestimoController {

    private final SolicitacaoEmprestimoService solicitacaoEmprestimoService;

    public PedidoEmprestimoController(SolicitacaoEmprestimoService solicitacaoEmprestimoService) {
        this.solicitacaoEmprestimoService = solicitacaoEmprestimoService;
    }

    @PostMapping
    public ResponseEntity solicitaEmprestimo(@Valid @RequestBody PedidoEmprestimo pedidoEmprestimo) {

        solicitacaoEmprestimoService.solicitaEmprestimo(pedidoEmprestimo.getIdUsuario(), pedidoEmprestimo.getIdLivro());

        return ResponseEntity.ok().build();
    }
}
