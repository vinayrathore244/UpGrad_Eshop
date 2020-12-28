package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ProductRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class ProductValidatorImpl implements ProductValidator {
    @Override
    public void validateAddProduct(ProductRequest productRequest) throws APIException {
        if (productRequest.getName() == null || productRequest.getName().length() <= 0) {
            throw new APIException("Invalid Name!");
        }

        if (productRequest.getAvailableItems() < 0) {
            throw new APIException("Invalid Available Items!");
        }

        if (productRequest.getCategory() == null || productRequest.getCategory().length() <= 0) {
            throw new APIException("Invalid Category!");
        }

        if (productRequest.getDescription() == null || productRequest.getDescription().length() <= 0) {
            throw new APIException("Invalid Description!");
        }

        if (productRequest.getImageUrl() == null || productRequest.getImageUrl().length() <= 0) {
            throw new APIException("Invalid Image URL!");
        }

        if (productRequest.getManufacturer() == null || productRequest.getManufacturer().length() <= 0) {
            throw new APIException("Invalid Manufacturer!");
        }

        if (productRequest.getPrice() < 0) {
            throw new APIException("Invalid Price!");
        }
    }
}
