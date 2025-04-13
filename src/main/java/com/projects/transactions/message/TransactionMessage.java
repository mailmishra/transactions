package com.projects.transactions.message;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TransactionMessage implements Serializable {
    
    @JsonProperty
    @NotNull
    private LocalDateTime transactionTime;

    @NotNull
    @JsonProperty 
    private long customerId;

    @JsonProperty 
    @NotNull
    private long quantity;

    @JsonProperty 
    @NotBlank
    private String productCode;
    
}
