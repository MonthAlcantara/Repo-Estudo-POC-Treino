package io.github.monthalcantara.aws_project02.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//Se tiver um campo desconheico, não mapeado, desconsidere
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsMessage {

    //Onde está a informação de fato
    @JsonProperty("Message")
    private String message;

    //O Tipo para poder logar
    @JsonProperty("Type")
    private String type;

    //Quem publicou
    @JsonProperty("TopicArn")
    private String topicArn;

    @JsonProperty("Timestamp")
    private String timestamp;

    //Id da mensagem que foi postada. Muito importante pra fazer o trace
    @JsonProperty("MessageId")
    private String messageId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopicArn() {
        return topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
