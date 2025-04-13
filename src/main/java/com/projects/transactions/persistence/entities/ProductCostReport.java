package com.projects.transactions.persistence.entities;

import java.math.BigDecimal;

public interface ProductCostReport {
    String getProductCode();
    BigDecimal getProductCost();
}
