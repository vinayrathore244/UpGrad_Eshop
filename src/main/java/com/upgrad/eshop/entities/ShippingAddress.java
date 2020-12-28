package com.upgrad.eshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ESHOP_SHIPPING_ADDRESS")
public class ShippingAddress {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String zipcode;
    @OneToOne
    private User user;
}
