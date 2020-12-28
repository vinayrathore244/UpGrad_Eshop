package com.upgrad.eshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ESHOP_ORDER")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @OneToOne
    private Product product;
    @ManyToOne
    private ShippingAddress shippingAddress;
    private Double amount;
    private LocalDateTime orderDate = LocalDateTime.now();
}
