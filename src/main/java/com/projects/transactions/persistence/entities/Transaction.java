package com.projects.transactions.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


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

    @NotNull
    private LocalDateTime transactionTime;

    @NotNull
    private long customerId;

    @NotNull
    private long quantity;

    @NotBlank
    @NotNull
    private String productCode;

}
