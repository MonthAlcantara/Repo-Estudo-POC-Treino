package io.github.monthalcantara.estudo.testecontainer.service;

public class RedisBackedCache {

    private final String host;

    private final Integer port;

    public RedisBackedCache(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String get(String test) {

        return  null;
    }

    public void put(String test, String example) {
    }
}
