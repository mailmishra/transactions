package com.projects.transactions.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import com.projects.transactions.message.TransactionMessage;
import com.projects.transactions.persistence.repository.ProductRepository;
import com.projects.transactions.persistence.repository.TransactionsRepository;
import com.projects.transactions.service.fixtures.TransactionFixtures;

@ExtendWith({MockitoExtension.class, OutputCaptureExtension.class})
public class TransactionServiceUnitTest {

    @Mock
    TransactionsRepository transactionsRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    public void testProcessTransaction_whenValidMessage_thenRepositoryUpdated() {
        //Given
        TransactionMessage transactionMessage = TransactionMessage.builder().customerId(10001).transactionTime(LocalDateTime.now())
        .productCode("PRODUCT_001").quantity(5).build();
        when(productRepository.findById(anyString())).thenReturn(TransactionFixtures.getActiveProduct());
        //when
        transactionService.processTransaction(transactionMessage);
        //then
        verify(transactionsRepository, times(1)).saveAndFlush(any());
    }

    @Test
    public void testProcessTransaction_whenInvalidProductInTransaction_thenDoNotAddTransactionToRepo(CapturedOutput output) {
        //Given
        TransactionMessage transactionMessage = TransactionMessage.builder().customerId(10001).transactionTime(LocalDateTime.now())
        .productCode("PRODUCT_002").quantity(5).build();
        when(productRepository.findById(anyString())).thenReturn(TransactionFixtures.getInActiveProduct());
        //When
        transactionService.processTransaction(transactionMessage);
        //Then
        verify(transactionsRepository, times(0)).saveAndFlush(any());
        assertTrue(output.getOut().contains("Inactive Product in transaction"));

    }

    @Test
    public void testProcessTransaction_whenCostExeceededTransaction_thenDoNotAddTransactionToRepo(CapturedOutput output) {
        //Given
        TransactionMessage transactionMessage = TransactionMessage.builder().customerId(10001).transactionTime(LocalDateTime.now())
        .productCode("PRODUCT_001").quantity(500).build();
        when(productRepository.findById(anyString())).thenReturn(TransactionFixtures.getActiveProduct());
        //When
        transactionService.processTransaction(transactionMessage);
        //Then
        verify(transactionsRepository, times(0)).saveAndFlush(any());
        assertTrue(output.getOut().contains("Transaction cost threshold exceeded for current transaction"));

    }

    @Test
    public void testProcessTransaction_whenProductNotFound_thenDoNotAddTransactionToRepo(CapturedOutput output) {
        //Given
        TransactionMessage transactionMessage = TransactionMessage.builder().customerId(10001).transactionTime(LocalDateTime.now())
        .productCode("PRODUCT_001").quantity(5).build();
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        //when
        transactionService.processTransaction(transactionMessage);
        //then
        verify(transactionsRepository, times(0)).saveAndFlush(any());
        assertTrue(output.getOut().contains("Invalid Product"));
    }

}
