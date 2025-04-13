package com.projects.transactions.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.projects.transactions.message.TransactionMessage;

@Service
public class ProducerService {

    @Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${kafka.topic.transaction}")
	private String topicName;

	public void sendMessage(TransactionMessage msg) {
		kafkaTemplate.send(topicName, UUID.randomUUID().toString(), msg);
	}
}
