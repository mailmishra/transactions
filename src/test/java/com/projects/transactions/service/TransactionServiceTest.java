package com.projects.transactions.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.projects.transactions.TransactionsApplication;

@SpringBootTest(classes = TransactionsApplication.class)
@TestPropertySource( locations = "classpath:application.yml")
public class TransactionServiceTest {


}
