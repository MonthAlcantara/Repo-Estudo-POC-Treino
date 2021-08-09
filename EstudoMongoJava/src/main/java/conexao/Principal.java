package conexao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        /*
        * Ao adicionar o driver do mongo, mesmo sem indicar uma porta, o próprio driver consegue
        * identificar a porta onde o mongoDB está rodando
        * */
        MongoClient client = new MongoClient();

        //Pegando o banco de dados
        MongoDatabase alunos = client.getDatabase("test");
        //Pegando uma collection ja criada anteriormente (No SQL seria o equivalente a uma tabela)
        MongoCollection<Document> collection = alunos.getCollection("alunos");
//        collection.insertOne(new Document(Map.of("nome", "Jamille")));
        System.out.println(collection.find());
        //Fechando a conexão
        client.close();
    }
}
