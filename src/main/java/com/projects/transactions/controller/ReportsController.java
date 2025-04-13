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
import com.projects.transactions.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="reports")
@RequiredArgsConstructor
@Slf4j
public class ReportsController {

    private final ReportService reportService;

    @PreAuthorize("hasRole('REPORTS')")  
    @GetMapping(path="/costPerCustomer", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerCostReport>> getCustomerCostReport() {
        return new ResponseEntity<>(reportService.getCostPerCustomer(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('REPORTS')") 
    @GetMapping(path="/costPerProduct", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCostReport>> getCostPerProductReport() {
        return new ResponseEntity<>(reportService.getCostPerProduct(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping(path="/transactionsCount", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionByLocationReport>>getTransactionsCountForAustraliaReport() {
        return new ResponseEntity<>(reportService.getTransactionByLocationReports(), HttpStatus.OK);
    }

}
