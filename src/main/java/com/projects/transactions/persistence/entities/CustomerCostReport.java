package com.projects.transactions.persistence.entities;

import java.math.BigDecimal;

public interface CustomerCostReport {
    
    long getCustomerId();
    String getFirstName();
    String getLastName();
    BigDecimal getCustomerCost();

}
