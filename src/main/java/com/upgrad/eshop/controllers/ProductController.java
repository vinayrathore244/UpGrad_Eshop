package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.SearchRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.services.ProductService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private ProductService productService;

    @Autowired
    private APIAuthorizer apiAuthorizer;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public Page<Product> search(SearchRequest searchRequest) throws APIException {
        logger.debug("Processing search request for " + searchRequest.toString());

        return productService.search(searchRequest);
    }

    @PostMapping
    public Product addProduct(
            @RequestBody ProductRequest productRequest, @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException {
        logger.debug("Processing add product request for " + productRequest.toString());

        logger.debug("Authorizing user for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        logger.debug("User authorised for ROLE_ADMIN");

        logger.debug("Started product request validation for " + productRequest.toString());
        productValidator.validateAddProduct(productRequest);
        logger.debug("Completed product request validation");

        logger.debug("Adding product to database");
        return productService.addProduct(productRequest);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        logger.debug("Processing getProductById request for product id " + id);

        return productService.getProductById(id).orElseThrow(
                () -> new ProductNotFoundException("No Product found for ID " + id)
        );
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest,
            @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException, ProductNotFoundException {
        logger.debug("Processing update product request for product id " + id);

        logger.debug("Authorizing user for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        logger.debug("User authorized for ROLE_ADMIN.");

        if (productService.getProductById(id).isEmpty()) {
            throw new ProductNotFoundException("No Product found for ID " + id);
        }

        logger.debug("Updating product in database");
        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeProduct (
            @PathVariable Long id, @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException, ProductNotFoundException {
        logger.debug("Processing delete product request for product id " + id);

        logger.debug("Authorising user for ROLE_ADMIN. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.ADMIN, jwtToken);
        logger.debug("User authorized for ROLE_ADMIN.");

        if (productService.getProductById(id).isEmpty()) {
            throw new ProductNotFoundException("No Product found for ID " + id);
        }

        logger.debug("Deleting product from database.");
        productService.removeProduct(id);
        logger.debug("Product deleted from database");

        return ResponseEntity.ok("Successfully Deleted");
    }

    @GetMapping("/categories")
    public List<String> getCategories () {
        logger.debug("Processing get categories request");
        return productService.getAllCategories();
    }
}
