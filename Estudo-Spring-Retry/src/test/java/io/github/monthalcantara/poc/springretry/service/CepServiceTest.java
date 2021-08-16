package io.github.monthalcantara.poc.springretry.service;

import io.github.monthalcantara.poc.springretry.dto.EnderecoResponse;
import io.github.monthalcantara.poc.springretry.dto.EnderecoResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CepServiceTest {

//    @InjectMocks
//    private CepService cepServiceMock;
//
//    @Mock
//    private RestTemplate restTemplate;

     @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
//        /**
//         * Usando reflection para setar num atributo da classe um objeto mockado,
//         * uma vez que ele não me permite fazer isso pelo construtor
//         */
//        Field restTemplateField = CepService.class.getDeclaredField("restTemplate");
//        restTemplateField.setAccessible(true);
//        restTemplateField.set(cepServiceMock, restTemplate);
    }


    @Test
        //  @DisplayName("Deve chamar a API de cep uma única vez")
    public void deveConsultarApiDeCep() {
        final CepService cepService = new CepService();
        EnderecoResponseDto enderecoResponseDto = cepService.getEnderecoResponseDto("42702790");
        //  Assertions.assertNotNull(enderecoResponseDto);
    }

    @Test
//    @DisplayName("Deve chamar a API de cep três vezes")
    public void deveConsultarApiDeCepTresVezes() throws NoSuchFieldException, IllegalAccessException {
        CepService cepService = new CepService();
        /**
         * Usando reflection para setar num atributo da classe um objeto mockado,
         * uma vez que ele não me permite fazer isso pelo construtor
         */
//        Field restTemplateField = CepService.class.getDeclaredField("restTemplate");
//        restTemplateField.setAccessible(true);
//        restTemplateField.set(cepServiceMock, restTemplate);


//        Mockito.when(restTemplate.getForEntity("http://viacep.com.br/ws/42702790/json", EnderecoResponse.class)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        //  Assertions.assertThrows(HttpClientErrorException.class, () -> cepServiceMock.getEnderecoResponseDto("42702790"));
        try {
            cepService.getEnderecoResponseDto("a");
        } catch (HttpClientErrorException ex) {
            System.out.println("teste");
        }
//        Mockito.verify(restTemplate, Mockito.times(3)).getForEntity("http://viacep.com.br/ws/42702790/json", EnderecoResponse.class);
    }

}