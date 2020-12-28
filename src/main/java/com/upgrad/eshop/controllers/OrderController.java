package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.services.OrderService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    private OrderService orderService;

    @Autowired
    private APIAuthorizer apiAuthorizer;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public Order createOrder(
            @RequestBody OrderRequest orderRequest, @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException, ProductNotFoundException, ShippingAddressNotFoundException {
        logger.debug("Processing order request for product id " + orderRequest.getProductId());

        logger.debug("Authorizing user for ROLE_USER. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.USER, jwtToken);
        logger.debug("User authorised for ROLE_USER");

        logger.debug("Started order request validation for product id " + orderRequest.getProductId());
        orderValidator.validateOrderRequest(orderRequest);
        logger.debug("Completed order request validation for product id " + orderRequest.getProductId());

        logger.debug("Adding order to database");
        return orderService.addOrder(orderRequest);
    }
}
