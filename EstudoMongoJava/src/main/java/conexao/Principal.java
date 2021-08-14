package conexao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.List;

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
        System.out.println(collection.find());

        /*
         * Adicionando um novo documento (ítem) na collection (tabela)
         * com o método append eu consigo ir colocando mais atributos
         * */
        collection.insertOne(new Document("nome", "Fulano")
                        .append("dataNascimento", new Date(2022, 1, 5))
                        // Para inserir uma relação é possivel criar um novo document filho dentro do Document pai
                        .append("curso", new Document("Curso", "Historia"))
//               Para adicionar listas
                        .append("notas", List.of(8, 7.5, 9, 6))
//                Para lista de objetos
                        .append("habilidades", List.of(new Document("Inglês", "avançado"), new Document("Italiano", "Basico")))
        );

        //Fechando a conexão
        client.close();
    }
}
