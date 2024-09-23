package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderServiceTxProxy implements OrderService {

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(transactionManager).execute(status -> {
            return target.createOrder(no, total);
        });
    }

    @Override
    public List<Order> createOrders(List<OrderReq> orderReqs) {
        return new TransactionTemplate(transactionManager).execute(status -> {
            return target.createOrders(orderReqs);
        });
    }

    @Override
    public Order getOrder(Long id) {
        return target.getOrder(id);
    }
}
