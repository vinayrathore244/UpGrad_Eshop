package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;

public interface OrderService {
    public Order addOrder(OrderRequest orderRequest) throws ProductNotFoundException, ShippingAddressNotFoundException;
    public Order getOrderById(Long id) throws OrderNotFoundException;
    public Order saveOrder(Order order);
}
