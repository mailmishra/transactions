package com.projects.transactions.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.transactions.persistence.entities.CustomerCostReport;
import com.projects.transactions.persistence.entities.ProductCostReport;
import com.projects.transactions.persistence.entities.TransactionByLocationReport;
import com.projects.transactions.persistence.repository.CustomerRepository;
import com.projects.transactions.persistence.repository.ProductRepository;
import com.projects.transactions.persistence.repository.TransactionsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="reports")
@RequiredArgsConstructor

public class ReportsController {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final TransactionsRepository transactionsRepository;

    @PreAuthorize("hasRole('ADMIN')")  
    @GetMapping(path="/costPerCustomer", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerCostReport>> getCustomerCostReport() {
        return new ResponseEntity<>(customerRepository.findCostPerCustomer(), HttpStatus.OK);
    }

    @GetMapping(path="/costPerProduct", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCostReport>> getCostPerProductReport() {
        return new ResponseEntity<>(productRepository.findCostPerProduct(), HttpStatus.OK);
    }

    @GetMapping(path="/transactionsCount", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionByLocationReport>>getTransactionsCountForAustraliaReport() {
        return new ResponseEntity<>(transactionsRepository.findTransactionsCountByLocation("Australia"), HttpStatus.OK);
    }

}
