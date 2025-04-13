package com.projects.transactions.persistence.entities;

import java.time.LocalDateTime;

import com.projects.transactions.validators.TransactionTimeToday;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Keeping Transactions DB without any foreign key as injestion performance is key.
 * But if customer_Id & productId validation is needed OneToMany relationship can be added  with Lazy loading to this entity.
 * Additionaly a foreign key on the table can be added, so as to ensure, invalid Product and Customer Id' can be rejected.
 */
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(schema = "transactions", name = "customer_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long transactionId;

    @TransactionTimeToday
    private LocalDateTime transactionTime;

    @NotNull
    private long customerId;

    @NotNull
    private long quantity;

    @NotBlank
    @NotNull
    private String productCode;

}
