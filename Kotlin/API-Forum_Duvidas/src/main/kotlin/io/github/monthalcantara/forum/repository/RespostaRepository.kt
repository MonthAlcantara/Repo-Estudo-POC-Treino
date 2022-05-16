package io.github.monthalcantara.forum.repository

import io.github.monthalcantara.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long>