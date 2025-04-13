package com.projects.transactions.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import com.projects.transactions.TransactionsApplication;
import com.projects.transactions.message.TransactionMessage;
import com.projects.transactions.persistence.entities.Transaction;
import com.projects.transactions.persistence.repository.TransactionsRepository;

/* Kafka Transaction Listener End to End testing */
@SpringBootTest(classes = TransactionsApplication.class)
@TestPropertySource( locations = "classpath:application.yml")
@DirtiesContext
public class TransactionServiceTest {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void testProcessTransaction_whenValidMessage_saveToRepository() {

        //Given
        LocalDateTime currentTime = LocalDateTime.now();
        TransactionMessage sentTransactionMessage = TransactionMessage.builder().customerId(10001).transactionTime(currentTime)
        .productCode("PRODUCT_001").quantity(5).build();
        
        //When
        producerService.sendMessage(sentTransactionMessage);
        
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Then
        List<Transaction> transactionList = transactionsRepository.findByCustomerIdAndTransactionTime(10001, currentTime);
        Transaction transaction = transactionList.stream().findFirst().get();
        assertAll(() -> {
            assertEquals(1, transactionList.size());
            assertEquals("PRODUCT_001", transaction.getProductCode());
            assertEquals(5, transaction.getQuantity());
        });
    }

    @Test
    public void testProcessTransaction_whenInValidMessage_messageNotSavedToRepository() {

        //Given
        LocalDateTime currentTime = LocalDateTime.now();
        TransactionMessage sentTransactionMessage = TransactionMessage.builder().customerId(10002).transactionTime(currentTime)
        .productCode("PRODUCT_001").quantity(500).build();
        
        //When
        producerService.sendMessage(sentTransactionMessage);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Then
        List<Transaction> transactionList = transactionsRepository.findByCustomerIdAndTransactionTime(10002, currentTime);
        assertEquals(0, transactionList.size());
    }

    @Test
    public void testProcessTransaction_whenPastDayTransactionTime_messageNotSavedToRepository() {

        //Given
        LocalDateTime currentTime = LocalDateTime.now().minusDays(5);
        TransactionMessage sentTransactionMessage = TransactionMessage.builder().customerId(10002).transactionTime(currentTime)
        .productCode("PRODUCT_001").quantity(5).build();
        
        //When
        producerService.sendMessage(sentTransactionMessage);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Then
        List<Transaction> transactionList = transactionsRepository.findByCustomerIdAndTransactionTime(10002, currentTime);
        assertEquals(0, transactionList.size());
    }

    @Test
    public void testProcessTransaction_whenNullInTransactionTime_messageNotSavedToRepository() {

        //Given
        LocalDateTime currentTime = null;
        TransactionMessage sentTransactionMessage = TransactionMessage.builder().customerId(10002).transactionTime(currentTime)
        .productCode("PRODUCT_001").quantity(5).build();
        
        //When
        producerService.sendMessage(sentTransactionMessage);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Then
        List<Transaction> transactionList = transactionsRepository.findByCustomerIdAndTransactionTime(10002, currentTime);
        assertEquals(0, transactionList.size());
    }

}
