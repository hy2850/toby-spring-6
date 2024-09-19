package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private final OrderRepositoryWithSpring orderRepository;
    private final JpaTransactionManager transactionManager;

    public OrderService(OrderRepositoryWithSpring orderRepository, JpaTransactionManager transactionManager) {
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
