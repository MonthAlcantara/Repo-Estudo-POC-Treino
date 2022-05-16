package io.github.monthalcantara.forum.service

import io.github.monthalcantara.forum.dto.request.NovoTopicoRequest
import io.github.monthalcantara.forum.dto.response.TopicoResponse
import io.github.monthalcantara.forum.model.Topico
import io.github.monthalcantara.forum.repository.CursoRepository
import io.github.monthalcantara.forum.repository.TopicoRepository
import io.github.monthalcantara.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@Service
class CadastraTopicoService(val usuarioRepository: UsuarioRepository, val cursoRepository: CursoRepository, val topicoRepository: TopicoRepository) {

    @Transactional
    fun executa(form: NovoTopicoRequest): TopicoResponse {
        val (_,_,idCurso, idAutor) = form

        val autor = usuarioRepository.findById(idAutor).orElseThrow { IllegalArgumentException("Autor não encontrado") }

        val curso = cursoRepository.findById(idCurso).orElseThrow { IllegalArgumentException("Curso não encontrado") }

        val entity = form.toEntity(usuario = autor, curso = curso)

        return TopicoResponse.toResponse(topicoRepository.save(entity))
    }



}
