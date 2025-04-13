package com.projects.transactions.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.transactions.message.TransactionMessage;
import com.projects.transactions.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "kafka.topic.consumer-type" , havingValue = "binary")
public class KakfaBinaryListener {

    private final TransactionService transactionService;

    @KafkaListener(topics = "${kafka.topic.transaction}", groupId = "${spring.kafka.consumer.group-id}")
    public void processTransaction(ConsumerRecord<String, byte[]> cr, @Payload byte[] payload) {
        TransactionMessage transactionMessage = null;
        try {
            ObjectMapper objMapper = new ObjectMapper();
             objMapper.reader().readValue(payload, TransactionMessage.class);
            log.info("Event Logged in transaction_events_topic - {}", transactionMessage);
            transactionService.processTransaction(transactionMessage);
        } catch (Exception e) {
            //Error processing - at this moment just logging and existing to deque the message.
            log.error("Error processing message={}", transactionMessage, e);
        }
    }

}

