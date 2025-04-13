package com.projects.transactions.persistence.entities;

import java.math.BigDecimal;

import com.projects.transactions.common.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String productCode;

    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private Status status;
}
