package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import io.github.monthalcantara.bibliotecaCDD.usuario.Usuario;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Set;

@Service
public class SolicitacaoEmprestimoService {

    private EntityManager manager;

    public SolicitacaoEmprestimoService(EntityManager manager) {
        this.manager = manager;
    }

    public void solicitaEmprestimo(Integer idUsuario, Set<LivroEmprestimoRequest> livros) {
//        Usuario usuario = manager.find(Usuario.class, idUsuario);
//        livros.stream().map(
//
//        )
    }
}
