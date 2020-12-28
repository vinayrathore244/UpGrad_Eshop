package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.ProductRepository;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.SearchRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.utils.DtoToEntityConverter;
import com.upgrad.eshop.utils.ProductComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product = dtoToEntityConverter.toProduct(productRequest);

        logger.debug("Saving product to database. product [" + product.toString() + "]");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequest productRequest) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(product -> productRepository.save(dtoToEntityConverter.toUpdateProduct(product, productRequest)))
                .orElseThrow(() -> new ProductNotFoundException("Product with product id " + id + " not found."));
    }

    @Override
    public Product removeItems(Long id, Integer count) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(product -> {
                    product.setAvailableItems(product.getAvailableItems() - count);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product with product id " + id + " not found."));
    }

    @Override
    public void removeProduct(Long id) throws ProductNotFoundException {
        productRepository.findById(id)
                .map(product -> {
                    product.setAvailableItems(0);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product with product id " + id + " not found."));
    }

    @Override
    public Page<Product> search(SearchRequest searchRequest) throws APIException {
        Stream<Product> products = productRepository.findAll().stream();

        if (searchRequest.getCategory() != null && !searchRequest.getCategory().isBlank()) {
            products = products.filter(product -> product.getCategory().equals(searchRequest.getCategory()));
        }

        if (searchRequest.getName() != null && !searchRequest.getName().isBlank()) {
            products = products.filter(product -> product.getName().startsWith(searchRequest.getName()));
        }

        products = products.sorted(ProductComparator.getComparatorByProperty(searchRequest.getSortBy()));

        List<Product> productList = products.collect(Collectors.toList());

        Pageable pageable = PageRequest.of(
                searchRequest.getPageNo(), searchRequest.getPageSize(), Sort.by(searchRequest.getDirection(), searchRequest.getSortBy()));

        PagedListHolder<Product> pagedListHolder = new PagedListHolder<>(productList);
        pagedListHolder.setPage(pageable.getPageNumber());
        pagedListHolder.setPageSize(pageable.getPageSize());
        pagedListHolder.setSort(
                new SortDefinition() {
                    @Override
                    public String getProperty() {
                        return searchRequest.getSortBy();
                    }

                    @Override
                    public boolean isIgnoreCase() {
                        return true;
                    }

                    @Override
                    public boolean isAscending() {
                        return searchRequest.getDirection() == Sort.Direction.ASC;
                    }
                }
        );

        return new PageImpl<>(pagedListHolder.getPageList(), pageable, productList.size());
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).stream().findFirst().orElse(null);
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
