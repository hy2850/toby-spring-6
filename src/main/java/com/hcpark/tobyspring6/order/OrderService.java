package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepositoryWithSpring orderRepository;

    public OrderService(OrderRepositoryWithSpring orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order newOrder = new Order(no, total);

        orderRepository.save(newOrder);

        return newOrder;
    }
}
