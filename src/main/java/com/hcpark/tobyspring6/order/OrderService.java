package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order newOrder = new Order(no, total);

//        orderRepository.save(newOrder);
//        return newOrder;

        return new TransactionTemplate(transactionManager).execute(status -> {
            orderRepository.save(newOrder);
            return newOrder;
        });
    }
}
