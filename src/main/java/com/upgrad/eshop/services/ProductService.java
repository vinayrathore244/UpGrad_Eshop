package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.SearchRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();
    public Optional<Product> getProductById(Long id);
    public Product addProduct(ProductRequest productRequest);
    public Product updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException;
    public Product removeItems(Long id, Integer count) throws ProductNotFoundException;
    public void removeProduct(Long id) throws ProductNotFoundException;
    public Page<Product> search(SearchRequest searchRequest) throws APIException;
    public Product getProductByName(String name);
    public List<String> getAllCategories();
    public Product saveProduct(Product product);
}
