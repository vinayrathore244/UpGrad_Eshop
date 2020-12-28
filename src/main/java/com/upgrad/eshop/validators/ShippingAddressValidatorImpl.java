package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ShippingAddressValidatorImpl implements ShippingAddressValidator {

    @Override
    public void validateAddAddress(ShippingAddressRequest shippingAddressRequest) throws APIException {

        if (shippingAddressRequest.getCity() == null || shippingAddressRequest.getCity().length() <= 0) {
            throw new APIException("Invalid City!");
        }

        if (shippingAddressRequest.getName() == null || shippingAddressRequest.getName().length() <= 0) {
            throw new APIException("Invalid Name!");
        }

        if (shippingAddressRequest.getStreet() == null || shippingAddressRequest.getStreet().length() <= 0) {
            throw new APIException("Invalid Street!");
        }

        if (shippingAddressRequest.getState() == null || shippingAddressRequest.getState().length() <= 0) {
            throw new APIException("Invalid State!");
        }

        if (shippingAddressRequest.getZipcode() == null ||
                !Pattern.matches("^\\d{6}$", shippingAddressRequest.getZipcode())) {
            throw new APIException("Invalid zip code!");
        }

        if (shippingAddressRequest.getPhone() == null ||
                !Pattern.matches("^\\d{10}$", shippingAddressRequest.getPhone())) {
            throw new APIException("Invalid contact number!");
        }
    }
}
