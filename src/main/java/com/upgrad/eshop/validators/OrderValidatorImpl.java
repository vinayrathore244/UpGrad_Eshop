package com.upgrad.eshop.validators;

import com.upgrad.eshop.daos.ProductRepository;
import com.upgrad.eshop.daos.ShippingAddressRepository;
import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderValidatorImpl implements OrderValidator {

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void validateOrderRequest(OrderRequest orderRequest) throws APIException {
        if (shippingAddressRepository.findById(orderRequest.getAddressId()).isEmpty()) {
            throw new APIException("No Address found for ID " + orderRequest.getAddressId());
        }

        if (productRepository.findById(orderRequest.getProductId()).isEmpty()) {
            throw new APIException("No Product found for ID " + orderRequest.getProductId());
        }

        if (productRepository.findById(orderRequest.getProductId()).get().getAvailableItems() <= 0) {
            throw new APIException("Product with ID " + orderRequest.getProductId() + " is currently out of stock!");
        }
    }
}
