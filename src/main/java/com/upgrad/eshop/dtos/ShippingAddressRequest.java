package com.upgrad.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressRequest {
    private String name;
    private String phone;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String zipcode;
}
