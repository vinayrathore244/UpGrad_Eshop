package com.upgrad.eshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.upgrad.eshop.controllers.ProductController;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.SearchRequest;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.validators.ProductValidator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testUtils.IntegrationTestRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ProductControllerTest extends IntegrationTestRunner {

    @SpyBean
    private ProductValidator productValidator;

    @SpyBean
    private APIAuthorizer apiAuthorizer;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ProductController productController;

    @Test
    public void testSearch() throws APIException, JsonProcessingException {
        Page<Product> productPage = productController.search(new SearchRequest());
        MatcherAssert.assertThat(productPage.get().count(), Matchers.equalTo(10L));
    }

    @Test
    public void testAddProduct() throws APIException, IOException {
        Mockito.doNothing().when(apiAuthorizer).authorizeFor(Mockito.anyString(), Mockito.anyString());
        Mockito.doNothing().when(productValidator).validateAddProduct(Mockito.any());
        Mockito.when(jwtTokenProvider.getUsernameFromToken(Mockito.anyString())).thenReturn("admin");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
        String token = "token";
        ProductRequest productRequest = ProductRequest.getProductRequest(
                "Test Product 1",
                "Test Category 1",
                2000,
                "Test Description 1",
                "Test Manufacturer 1",
                30,
                "test url 1"
        );
        ResponseEntity<Product> responseEntity = getResponseEntity(
                "/products", HttpMethod.POST, token, productRequest, Product.class
        );
        Product product = responseEntity.getBody();
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Test Product 1", product.getName());
    }

    @Test
    public void testGetProductById() throws JsonProcessingException {
        ResponseEntity<Product> responseEntity = getResponseEntity(
                "/products/4", HttpMethod.GET, null, null, Product.class
        );
        Product product = responseEntity.getBody();
        Assertions.assertNotNull(product);
        Assertions.assertEquals(4, product.getProductId());
    }

    @Test
    public void testUpdateProduct() throws APIException, JsonProcessingException {
        Mockito.doNothing().when(apiAuthorizer).authorizeFor(Mockito.anyString(), Mockito.anyString());
        Mockito.when(jwtTokenProvider.getUsernameFromToken(Mockito.anyString())).thenReturn("admin");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
        String token = "token";
        ProductRequest productRequest = ProductRequest.getProductRequest(
                "Test Product 1",
                "Test Category 1",
                2000,
                "Test Description 1",
                "Test Manufacturer 1",
                30,
                "test url 1"
        );
        ResponseEntity<Product> responseEntity = getResponseEntity(
                "/products/4", HttpMethod.PUT, token, productRequest, Product.class
        );
        Product product = responseEntity.getBody();
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Test Product 1", product.getName());
    }

    @Test
    public void testRemoveProduct() throws APIException, JsonProcessingException {
        Mockito.doNothing().when(apiAuthorizer).authorizeFor(Mockito.anyString(), Mockito.anyString());
        Mockito.when(jwtTokenProvider.getUsernameFromToken(Mockito.anyString())).thenReturn("admin");
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString(), Mockito.any())).thenReturn(true);
        String token = "token";
        ResponseEntity<String> responseEntity = getResponseEntity(
                "/products/10", HttpMethod.DELETE, token, null, String.class
        );
        String message = responseEntity.getBody();
        Assertions.assertNotNull(message);
        Assertions.assertEquals("Successfully Deleted", message);
    }

    @Test
    public void testGetCategories() throws JsonProcessingException {
        ParameterizedTypeReference<List<String>> typeReference = new ParameterizedTypeReference<List<String>>() {
        };
        ResponseEntity<List<String>> responseEntity = getResponseEntity(
                "/products/categories", HttpMethod.GET, null, null, typeReference);
        List<String> categories = responseEntity.getBody();
        Assertions.assertNotNull(categories);
        Assertions.assertTrue(categories.size() > 0);
    }
}
