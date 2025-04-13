package com.projects.transactions.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projects.transactions.persistence.entities.Product;
import com.projects.transactions.persistence.entities.ProductCostReport;

public interface ProductRepository extends JpaRepository<Product,String>{

    @Query(nativeQuery = true, value = "Select p.product_code as productCode, sum(p.cost * ct.quantity) as productCost " + //
                "from customer_transaction ct, product p  " + //
                "where ct.product_code = p.product_code " + //
                "group by p.product_code " + //
                "order by p.product_code")
    List<ProductCostReport> findCostPerProduct();

}
