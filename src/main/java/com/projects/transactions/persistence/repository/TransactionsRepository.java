package com.projects.transactions.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projects.transactions.persistence.entities.TransactionByLocationReport;
import com.projects.transactions.persistence.entities.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByCustomerIdAndTransactionTime(long customerId, LocalDateTime time);

    @Query(nativeQuery = true, value = "Select ct.customer_id as customerId, max(c.first_name) as firstName, max(c.last_name) as lastName, count(ct.*) as transactionCount " + 
                "from customer c, customer_transaction ct " + 
                "where c.customer_id = ct.customer_id  " + 
                "and c.location_country = :location " + 
                "group by ct.customer_id " + 
                "order by ct.customer_id" )
    List<TransactionByLocationReport> findTransactionsCountByLocation(@Param("location") String location);

}
