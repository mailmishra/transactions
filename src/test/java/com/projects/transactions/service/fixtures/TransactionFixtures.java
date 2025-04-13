package com.projects.transactions.service.fixtures;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.projects.transactions.common.Status;
import com.projects.transactions.persistence.entities.Product;

public class TransactionFixtures {

    public static List<Product> getAllProducts() {
        return List.of(getActiveProduct().get(), getInActiveProduct().get());
    }

    public static Optional<Product> getActiveProduct() {
        return Optional.of(Product.builder().productCode("PRODUCT_001").status(Status.Active).cost(BigDecimal.valueOf(50)).build());
    }

    public static Optional<Product> getInActiveProduct() {
        return Optional.of(Product.builder().productCode("PRODUCT_002").status(Status.Inactive).cost(BigDecimal.valueOf(100)).build());
    }

}
