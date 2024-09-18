package com.hcpark.tobyspring6;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.hcpark.tobyspring6.order.Order;
import com.hcpark.tobyspring6.order.OrderRepositoryWithSpring;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);

//        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
//        Order newOrder = new Order("100", BigDecimal.TEN);
//        orderRepository.save(newOrder);
//        Order newOrder2 = new Order("100", BigDecimal.TEN);
//        orderRepository.save(newOrder2);

        OrderRepositoryWithSpring orderRepository = beanFactory.getBean(OrderRepositoryWithSpring.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute(status -> {
                Order newOrder = new Order("100", BigDecimal.TEN);
                orderRepository.save(newOrder);

                Order newOrder2 = new Order("100", BigDecimal.TEN);
                orderRepository.save(newOrder2);

                return null;
            });
        } catch (DataIntegrityViolationException e) {
            System.out.println("중복된 유저 주문 처리");
            // 예외 처리 비즈니스 로직 여기에
        }
    }
}
