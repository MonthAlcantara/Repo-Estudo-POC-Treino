Para execução desse projeto os seguintes passos devem ser seguidos:

1 - Subir o localstack com o docker compose

````
$ docker-compose up
````

2 - Rodar a aplicação Producer

3 - Rodar a aplicação Consumer

4 - Realizar a chamada no postman para o endpoint ``` http://localhost:8081/v1/producer ``` com o payload:
```
{
    "nome" : "algumNome",
    "conteudo": "algumConteudo"
}
```
