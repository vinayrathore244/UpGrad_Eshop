package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.OrderRepository;
import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.entities.Order;
import com.upgrad.eshop.exceptions.OrderNotFoundException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.utils.DtoToEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order addOrder(OrderRequest orderRequest) throws ProductNotFoundException, ShippingAddressNotFoundException {
        Order order = dtoToEntityConverter.toOrder(orderRequest);
        logger.debug("Saving order to database. Order [" + order.toString() + "]");
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) throws OrderNotFoundException {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order with order id " + id + " not found."));
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
