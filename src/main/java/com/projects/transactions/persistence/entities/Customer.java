package com.projects.transactions.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@SequenceGenerator(name="customer_id_seq", initialValue=10000 )
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String location;

}
