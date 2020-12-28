package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.ShippingAddressRepository;
import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.utils.DtoToEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(ShippingAddressServiceImpl.class);

    @Override
    public ShippingAddress addShippingAddress(ShippingAddressRequest shippingAddressRequest) {
        ShippingAddress shippingAddress = dtoToEntityConverter.toShippingAddress(shippingAddressRequest);

        logger.debug("Saving shipping address to database. Shipping Address [" + shippingAddress + "]");
        return shippingAddressRepository.save(shippingAddress);
    }

    @Override
    public ShippingAddress getShippingAddressById(Long id) throws ShippingAddressNotFoundException {
        return shippingAddressRepository.findById(id).orElseThrow(
                () -> new ShippingAddressNotFoundException("Shipping Address with id " + id + " not found!"));
    }

    @Override
    public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }
}
