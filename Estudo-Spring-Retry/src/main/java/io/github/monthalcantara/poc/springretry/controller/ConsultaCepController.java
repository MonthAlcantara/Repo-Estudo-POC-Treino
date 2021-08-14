package io.github.monthalcantara.poc.springretry.controller;

import io.github.monthalcantara.poc.springretry.dto.EnderecoResponseDto;
import io.github.monthalcantara.poc.springretry.service.CepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/cep")
public class ConsultaCepController {

    private CepService cepService;

    public ConsultaCepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponseDto> buscaCep(@PathVariable(value = "cep") String cep) {

        log.info("Recebido cep %s para consulta", cep);

        final EnderecoResponseDto enderecoResponseDto = cepService.getEnderecoResponseDto(cep);

        log.info("Consulta a API de Cep realizada com sucesso", enderecoResponseDto);

        return ResponseEntity.ok(enderecoResponseDto);
    }


}
