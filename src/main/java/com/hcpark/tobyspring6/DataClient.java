package com.hcpark.tobyspring6;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hcpark.tobyspring6.order.Order;
import com.hcpark.tobyspring6.order.OrderRepository;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order newOrder = new Order("100", BigDecimal.TEN);
        var result = orderRepository.save(newOrder);

        System.out.println("result : " + result);
    }

}
