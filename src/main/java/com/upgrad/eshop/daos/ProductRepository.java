package com.upgrad.eshop.daos;

import com.upgrad.eshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByName(String name);

    @Query("SELECT DISTINCT p.category FROM Product p")
    public List<String> findDistinctCategories();
}
