package io.github.monthalcantara.forum.repository

import io.github.monthalcantara.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long>