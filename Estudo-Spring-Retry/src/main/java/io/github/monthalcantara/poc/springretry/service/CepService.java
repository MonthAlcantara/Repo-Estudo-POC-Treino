package io.github.monthalcantara.poc.springretry.service;

import io.github.monthalcantara.poc.springretry.dto.EnderecoResponse;
import io.github.monthalcantara.poc.springretry.dto.EnderecoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CepService {
    private final String url = "http://viacep.com.br/ws/{cep}/json";
    final RestTemplate restTemplate = new RestTemplate();

    public EnderecoResponseDto getEnderecoResponseDto(String cep) {

        try {
            final Map<String, String> params = new HashMap<>();
            params.put("cep", cep);

            final URI expanded = new UriTemplate(url).expand(params);

            log.info("Iniciada chamada a api de Cep");

            final EnderecoResponse enderecoResponse = restTemplate.getForEntity(expanded.toString(), EnderecoResponse.class).getBody();

            log.info("Consulta a API de Cep concluída com sucesso", enderecoResponse);

            final EnderecoResponseDto enderecoResponseDto = enderecoResponse.toDto();

            log.info("Conversão para objeto de saída realizada com sucesso", enderecoResponseDto);
            return enderecoResponseDto;
        } catch (Exception ex) {
            return null;
        }
    }
}
