package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.ShippingAddressRequest;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;

public interface ShippingAddressService {
    public ShippingAddress addShippingAddress(ShippingAddressRequest shippingAddressRequest);
    public ShippingAddress getShippingAddressById(Long id) throws ShippingAddressNotFoundException;
    public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress);
}
