package com.christinagorina.service;

import com.christinagorina.config.RequestReplyingKafkaTemplate;
import com.christinagorina.model.Answer;
import com.christinagorina.model.Requisition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class GatewayService {

    private final RequestReplyingKafkaTemplate requestReplyingKafkaTemplate;
    private final ObjectMapper objectMapper;

    public Answer gatewayInput(Requisition message) throws ExecutionException, InterruptedException, JsonProcessingException {
        log.info("gatewayInput message = " + message);

        Answer answer = objectMapper.readValue(
                requestReplyingKafkaTemplate.sendAndReceive(new ProducerRecord<>("gateway.torequisition", message)).get().value(),
                Answer.class);
        log.info("answer = " + answer);
        return answer;

    }

}
