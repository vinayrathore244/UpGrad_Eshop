package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface ShippingAddressValidator {
    public void validateAddAddress (ShippingAddressRequest shippingAddressRequest) throws APIException;
}
