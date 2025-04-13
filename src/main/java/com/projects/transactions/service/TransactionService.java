package com.projects.transactions.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.transactions.common.Status;
import com.projects.transactions.message.TransactionMessage;
import com.projects.transactions.persistence.entities.Product;
import com.projects.transactions.persistence.entities.Transaction;
import com.projects.transactions.persistence.repository.ProductRepository;
import com.projects.transactions.persistence.repository.TransactionsRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final ProductRepository productRepository;
    private final ProducerService service;

    public void processTransaction(TransactionMessage payload) {
        try {
            this.validateTransaction(payload);
            this.insertTransaction(payload);
        } catch (Exception e) {
            //Error processing - at this moment just logging and existing to deque the message.
            log.error("Error processing message={}", payload, e);
        }
    }

    /*
     * Individual transaction updates, adds new record, hence avoiding @Tranasactional
     */
    private void insertTransaction(TransactionMessage tranasctionMsg) throws Exception{
        Transaction t  = Transaction.builder().customerId(tranasctionMsg.getCustomerId()).productCode(tranasctionMsg.getProductCode())
        .transactionTime(tranasctionMsg.getTransactionTime()).quantity(tranasctionMsg.getQuantity())
        .build();
        transactionsRepository.saveAndFlush(t);
    }

    /*
     * Business validations
     */
    private void validateTransaction( TransactionMessage transactionMessage) throws RuntimeException {
        Product product = this.getProductById(transactionMessage.getProductCode());
        if(product.getStatus().equals(Status.Inactive)) {
            throw new RuntimeException("Inactive Product in transaction=" + product.getProductCode());
        }
        BigDecimal transactionCost = BigDecimal.valueOf(transactionMessage.getQuantity()).multiply(product.getCost());
        if(transactionCost.compareTo(BigDecimal.valueOf(5000)) > 0) {
            throw new RuntimeException("Transaction cost threshold exceeded for current transaction="+ transactionCost);
        } 
    }

    /*
     * Enabled caching on products for faster transaction processing, can add expiry/evict on Product Update.
     */
    @Cacheable(value = "products", key = "#id")
    private Product getProductById(String id) throws RuntimeException {
        Optional<Product> productOpt = this.productRepository.findById(id);
        if(productOpt.isPresent()) {
            return productOpt.get();
        } else {
            throw new IllegalArgumentException(String.format("Invalid Product=%S in message", id));
        }
    }

    // @PostConstruct
    // public void postConstruct() {
        
        
    // }
}
