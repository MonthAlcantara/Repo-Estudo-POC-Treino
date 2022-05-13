package io.github.monthalcantara.core.messaging;

public interface SqsQueueSender {

    void send(final String message);
}
