package com.christinagorina.config;

import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.GenericMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.time.Duration;

public class RequestReplyingKafkaTemplate extends ReplyingKafkaTemplate<String, Object, String> {

    public RequestReplyingKafkaTemplate(ProducerFactory<String, Object> producerFactory, GenericMessageListenerContainer<String, String> replyContainer) {
        super(producerFactory, replyContainer);
        this.setDefaultReplyTimeout(Duration.ofMinutes(2));
    }

}
