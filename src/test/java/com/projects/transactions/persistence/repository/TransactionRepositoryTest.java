package com.projects.transactions.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.projects.transactions.persistence.entities.TransactionByLocationReport;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest
@Sql(scripts = {"/db/add_customer_transaction_records.sql"}, executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = {"/db/cleanup_customer_transaction_records.sql"}, executionPhase = AFTER_TEST_CLASS)
public class TransactionRepositoryTest {
    
    @Autowired
    TransactionsRepository transactionsRepository;

    @Test
    void testFindNoOfTransactionsByLocation() {
       List<TransactionByLocationReport> report = transactionsRepository.findTransactionsCountByLocation("Australia");
       assertEquals(2, report.size());
       assertEquals(3, report.stream().filter( r -> r.getCustomerId() == 10001).findFirst().get().getTransactionCount());
       assertEquals(2, report.stream().filter( r -> r.getCustomerId() == 10003).findFirst().get().getTransactionCount());
    }
}
