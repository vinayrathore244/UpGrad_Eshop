package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.services.ShippingAddressService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.validators.ShippingAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-addresses")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressValidator shippingAddressValidator;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private APIAuthorizer apiAuthorizer;

    private static final Logger logger = LoggerFactory.getLogger(ShippingAddressController.class);

    @PostMapping
    public ShippingAddress addAddress(
            @RequestBody ShippingAddressRequest shippingAddressRequest,
            @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken)
            throws APIException {
        logger.debug("Processing add address request for " + shippingAddressRequest.toString());

        logger.debug("Authorizing user for ROLE_USER. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.USER, jwtToken);
        logger.debug("User authorized for ROLE_USER");

        logger.debug("Started validating add address request");
        shippingAddressValidator.validateAddAddress(shippingAddressRequest);
        logger.debug("Completed validating add address request");

        logger.debug("Adding shipping request to the database");
        return shippingAddressService.addShippingAddress(shippingAddressRequest);
    }
}
