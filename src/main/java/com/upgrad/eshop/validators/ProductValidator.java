package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface ProductValidator {
    public void validateAddProduct(ProductRequest productRequest) throws APIException;
}
