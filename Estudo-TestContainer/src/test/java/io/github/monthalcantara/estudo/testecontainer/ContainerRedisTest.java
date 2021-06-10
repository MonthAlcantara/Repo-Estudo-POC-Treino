package io.github.monthalcantara.estudo.testecontainer;

import io.github.monthalcantara.estudo.testecontainer.service.RedisBackedCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.assertEquals;

@Testcontainers
public class ContainerRedisTest {

    private RedisBackedCache underTest;

    //Essa anotação diz a Junit para notificar este campo sobre os eventos no ciclo de vida do teste
    @Container
    //Container genérico
    public GenericContainer redis = new GenericContainer(DockerImageName
            //Imagem do docker Hub que será usada
            .parse("redis:5.0.3-alpine"))
            //A porta em que rodará
            .withExposedPorts(6379);

    @BeforeEach
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();


        underTest = new RedisBackedCache(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
        underTest.put("test", "example");

        String retrieved = underTest.get("test");
        assertEquals("example", retrieved);
    }
}
