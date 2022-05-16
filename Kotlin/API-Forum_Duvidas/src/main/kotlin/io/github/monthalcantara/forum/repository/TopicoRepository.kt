package io.github.monthalcantara.forum.repository

import io.github.monthalcantara.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long>