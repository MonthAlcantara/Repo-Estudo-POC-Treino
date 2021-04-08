package io.github.monthalcantara.bibliotecaCDD.emprestimo;

import io.github.monthalcantara.bibliotecaCDD.livro.Livro;
import io.github.monthalcantara.bibliotecaCDD.livro.LivroEmprestimoRequest;
import io.github.monthalcantara.bibliotecaCDD.usuario.TipoUsuario;
import io.github.monthalcantara.bibliotecaCDD.usuario.Usuario;
import io.github.monthalcantara.bibliotecaCDD.usuario.UsuarioResponse;
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
    public UsuarioResponse solicitaEmprestimo(Integer idUsuario, Set<LivroEmprestimoRequest> livros) {
        Set<Livro> livrosEncontrados = buscaLivros(livros);
        Usuario usuario = buscaUsuario(idUsuario);


        // Qual perfil do usuario?
        //pode pegar livro?
        //o livro está disponivel pra esse perfil?
        //
        for(Livro livro : livrosEncontrados){
            if(isLivroDisponivelParaUsuario(usuario,livro)){
                livro.setDisponivel(false);
                usuario.setQtdLivrosEmprestados(usuario.getQtdLivrosEmprestados() + 1);
            }
        }
return new UsuarioResponse(usuario);

        //        Usuario usuario = manager.find(Usuario.class, idUsuario);
//        livros.stream().map(
//
//        )
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

    private boolean isLivroDisponivelParaUsuario(Usuario usuario, Livro livro){
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();

        boolean restrito = livro.isRestrito();
        boolean disponivel = livro.isDisponivel();

        if(!usuario.podeSolicitarEmprestimo()){
            return false;
        }else if(TipoUsuario.PADRAO.equals(tipoUsuario)){
            return disponivel && !restrito;
        }else{
            return disponivel;
        }
    }
}
