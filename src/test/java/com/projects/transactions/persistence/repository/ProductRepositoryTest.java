package com.projects.transactions.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.projects.transactions.persistence.entities.ProductCostReport;

@SpringBootTest
@Sql(scripts = {"/db/cleanup_customer_transaction_records.sql"}, executionPhase = BEFORE_TEST_CLASS)
@Sql(scripts = {"/db/add_customer_transaction_records.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/cleanup_customer_transaction_records.sql"}, executionPhase = AFTER_TEST_CLASS)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    
    @Test
    void testFindCostPerProduct() {
       List<ProductCostReport> report = productRepository.findCostPerProduct();
       assertEquals(3, report.size());
       assertEquals(BigDecimal.valueOf(850), report.stream().filter( r -> r.getProductCode().equalsIgnoreCase("PRODUCT_001")).findFirst().get().getProductCost());
       assertEquals(BigDecimal.valueOf(6600), report.stream().filter( r -> r.getProductCode().equalsIgnoreCase("PRODUCT_003")).findFirst().get().getProductCost());
       assertEquals(BigDecimal.valueOf(10000), report.stream().filter( r -> r.getProductCode().equalsIgnoreCase("PRODUCT_005")).findFirst().get().getProductCost());
    }
}
