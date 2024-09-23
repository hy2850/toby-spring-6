package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order newOrder = new Order(no, total);
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> createOrders(List<OrderReq> orderReqs) {
        return orderReqs.stream().map(orderReq -> createOrder(orderReq.no(), orderReq.total())).toList();
    }

    @Override
    public Order getOrder(Long id) {
        try {
            return orderRepository.find(id);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
