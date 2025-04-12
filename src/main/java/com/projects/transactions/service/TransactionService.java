package com.projects.transactions.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.projects.transactions.persistence.entities.Transaction;
import com.projects.transactions.persistence.repository.TransactionsRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TransactionService {

    private final TransactionsRepository transactionsRepository;

    public void insertTransaction() {
        Transaction t  = Transaction.builder().customerId(10001).productCode("PRODUCT_001")
        .transactionTime(LocalDateTime.now()).quantity(5)
        .build();
        transactionsRepository.save(t);
        transactionsRepository.flush();
    }

    @PostConstruct
    public void postConstruct() {
        this.insertTransaction();
    }
}
