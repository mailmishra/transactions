package com.projects.transactions.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.transactions.persistence.entities.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long>{

}
