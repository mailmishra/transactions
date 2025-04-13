package com.projects.transactions.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projects.transactions.exception.ClientException;
import com.projects.transactions.persistence.entities.CustomerCostReport;
import com.projects.transactions.persistence.entities.ProductCostReport;
import com.projects.transactions.persistence.entities.TransactionByLocationReport;
import com.projects.transactions.persistence.repository.CustomerRepository;
import com.projects.transactions.persistence.repository.ProductRepository;
import com.projects.transactions.persistence.repository.TransactionsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final TransactionsRepository transactionsRepository;

    private static final String LOCATION = "Australia";

    public List<CustomerCostReport> getCostPerCustomer() {
        try {
            return customerRepository.findCostPerCustomer();
        } catch(Exception e) {
            log.error("Error fetching Cost per Customer report", e);
            throw new ClientException("Error processing request");
        }
    }

    public List<ProductCostReport> getCostPerProduct() {
        try {
            return productRepository.findCostPerProduct();
        } catch(Exception e) {
            log.error("Error fetching Cost per Product report", e);
            throw new ClientException("Error processing request");
        }
    }

    public List<TransactionByLocationReport> getTransactionByLocationReports() {
        try {
            return transactionsRepository.findTransactionsCountByLocation(LOCATION);
        } catch(Exception e) {
            log.error("Error fetching Transactions per Australian customer report", e);
            throw new ClientException("Error processing request");
        }
    }
}
