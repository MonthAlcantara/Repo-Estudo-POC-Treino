package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import io.github.monthalcantara.bibliotecaCDD.livro.Livro;
import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import io.github.monthalcantara.bibliotecaCDD.usuario.TipoUsuario;
import io.github.monthalcantara.bibliotecaCDD.usuario.Usuario;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SolicitacaoEmprestimoService {


    private EntityManager manager;

    public SolicitacaoEmprestimoService(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public Usuario solicitaEmprestimo(Integer idUsuario, Integer livro) {
        Usuario usuario = buscaUsuario(idUsuario);
        Livro livroEncontrado = buscaLivro(livro);

        if(usuario.podeSolicitarEmprestimo(livroEncontrado)){

        }

        return null;
    }

    private Livro buscaLivro(Integer idLivro) {
        return manager.find(Livro.class, idLivro);
    }

    private Usuario buscaUsuario(Integer idUsuario) {
        return manager.find(Usuario.class, idUsuario);
    }

    private Set<Livro> buscaLivros(Set<LivroEmprestimoRequest> livros) {
        return livros.stream()
                .map(l -> manager.find(Livro.class, l.getIdLivro()
                ))
                .collect(Collectors.toSet());
    }

}
