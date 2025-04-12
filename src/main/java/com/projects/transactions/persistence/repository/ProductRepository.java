package com.projects.transactions.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projects.transactions.persistence.entities.Product;

public interface ProductRepository extends JpaRepository<Product,String>{

}
