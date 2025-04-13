package com.projects.transactions.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.transactions.persistence.entities.Customer;
import com.projects.transactions.persistence.entities.CustomerCostReport;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "Select ct.customer_id as customerId, max(c.first_name) as firstName, max(c.last_name) as lastName, sum(p.cost * ct.quantity) as customerCost " +
            "from customer c, customer_transaction ct, product p " +
            "where c.customer_id = ct.customer_id " +
            "and p.product_code = ct.product_code " +
            "group by ct.customer_id " +
            "order by ct.customer_id")
    List<CustomerCostReport> findCostPerCustomer();
}
