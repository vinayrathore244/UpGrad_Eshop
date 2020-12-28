package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface OrderValidator {
    public void validateOrderRequest(OrderRequest orderRequest) throws APIException;
}
