package io.github.monthalcantara.projetoestudowebflux.controllers

import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Slf4j
@RestController
@RequestMapping("/v1/animes")
class AnimeController {

    @GetMapping
    fun teste(): Flux<String> {
        val flux = Flux.fromIterable(mutableListOf("a","b","c","d"))
            .log("log te teste")
            .map { it -> it.toUpperCase() }
        flux.subscribe()
            return flux
    }
}