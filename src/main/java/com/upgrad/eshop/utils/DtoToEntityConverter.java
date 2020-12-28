package com.upgrad.eshop.utils;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.services.ProductService;
import com.upgrad.eshop.services.ShippingAddressService;
import com.upgrad.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DtoToEntityConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private FieldsValidator fieldsValidator;

    public Order toOrder(OrderRequest orderRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        Order order = new Order();
        Product product = productService.getProductById(orderRequest.getProductId()).orElseThrow(
                () -> new ProductNotFoundException("Product with product id " + orderRequest.getProductId() + " not found!")
        );
        order.setUser(userService.getLoggedInUser());
        order.setProduct(product);
        order.setShippingAddress(shippingAddressService.getShippingAddressById(orderRequest.getAddressId()));
        order.setAmount(product.getPrice());
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public Product toProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setManufacturer(productRequest.getManufacturer());
        product.setAvailableItems(productRequest.getAvailableItems());
        product.setImageUrl(productRequest.getImageUrl());
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        return product;
    }

    public Product toUpdateProduct(Product product, ProductRequest productRequest) {
        if (fieldsValidator.isNotNullAndEmpty(productRequest.getName())) {
            product.setName(productRequest.getName());
        }

        if (fieldsValidator.isNotNullAndEmpty(productRequest.getCategory())) {
            product.setCategory(productRequest.getCategory());
        }

        if (fieldsValidator.isNotNullAndEmpty(productRequest.getPrice())) {
            product.setPrice(productRequest.getPrice());
        }

        if (fieldsValidator.isNotNullAndEmpty(productRequest.getDescription())) {
            product.setDescription(productRequest.getDescription());
        }

        if (fieldsValidator.isNotNullAndEmpty(product.getManufacturer())) {
            product.setManufacturer(productRequest.getManufacturer());
        }

        if (fieldsValidator.isNotNullAndEmpty(productRequest.getAvailableItems())) {
            product.setAvailableItems(productRequest.getAvailableItems());
        }

        if (fieldsValidator.isNotNullAndEmpty(productRequest.getImageUrl())){
            product.setImageUrl(productRequest.getImageUrl());
        }

        product.setUpdated(LocalDateTime.now());
        return product;
    }

    public ShippingAddress toShippingAddress(ShippingAddressRequest shippingAddressRequest) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setName(shippingAddressRequest.getName());
        shippingAddress.setPhone(shippingAddressRequest.getPhone());
        shippingAddress.setStreet(shippingAddressRequest.getStreet());
        shippingAddress.setLandmark(shippingAddressRequest.getLandmark());
        shippingAddress.setCity(shippingAddressRequest.getCity());
        shippingAddress.setState(shippingAddressRequest.getState());
        shippingAddress.setZipcode(shippingAddressRequest.getZipcode());
        shippingAddress.setUser(userService.getLoggedInUser());
        return shippingAddress;
    }

    public User toUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setRole(Constants.Roles.USER);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        return user;
    }
}
