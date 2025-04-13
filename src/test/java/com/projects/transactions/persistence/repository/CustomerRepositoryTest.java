package com.projects.transactions.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.projects.transactions.persistence.entities.CustomerCostReport;
@SpringBootTest
@Sql(scripts = {"/db/add_customer_transaction_records.sql"}, executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = {"/db/cleanup_customer_transaction_records.sql"}, executionPhase = AFTER_TEST_CLASS)
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testFindCostPerCustomer() {
       List<CustomerCostReport> report = customerRepository.findCostPerCustomer();
       assertEquals(3, report.size());
       assertEquals(BigDecimal.valueOf(9750), report.stream().filter( r -> r.getCustomerId() == 10001).findFirst().get().getCustomerCost());
       assertEquals(BigDecimal.valueOf(2200), report.stream().filter( r -> r.getCustomerId() == 10002).findFirst().get().getCustomerCost());
       assertEquals(BigDecimal.valueOf(5500), report.stream().filter( r -> r.getCustomerId() == 10003).findFirst().get().getCustomerCost());
    }
}
