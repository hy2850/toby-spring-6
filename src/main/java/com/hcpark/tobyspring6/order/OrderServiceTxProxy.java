package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderServiceTxProxy implements OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        Order newOrder = new Order(no, total);

        //        orderRepository.save(newOrder);
        //        return newOrder;

        return new TransactionTemplate(transactionManager).execute(status -> {
            orderRepository.save(newOrder);
            return newOrder;
        });
    }

    @Override
    public List<Order> createOrders(List<OrderReq> orderReqs) {
        //        return orderReqs.stream().map(orderReq -> createOrder(orderReq.no(), orderReq.total())).toList();

        return new TransactionTemplate(transactionManager).execute(status -> {
            return orderReqs.stream().map(orderReq -> createOrder(orderReq.no(), orderReq.total())).toList();
        });
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
