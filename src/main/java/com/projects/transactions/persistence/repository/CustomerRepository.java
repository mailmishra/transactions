package com.projects.transactions.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projects.transactions.persistence.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
