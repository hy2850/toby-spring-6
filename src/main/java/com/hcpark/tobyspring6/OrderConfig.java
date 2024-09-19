package com.hcpark.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hcpark.tobyspring6.order.OrderRepository;
import com.hcpark.tobyspring6.order.OrderRepositoryWithSpring;
import com.hcpark.tobyspring6.order.OrderService;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepositoryWithSpring());
    }

    // EntityManagerFactory 빈을 인자로 주입받음
    @Bean
    public OrderRepository orderRepository(EntityManagerFactory emf) {
        return new OrderRepository(emf);
    }

    @Bean
    public OrderRepositoryWithSpring orderRepositoryWithSpring() {
        return new OrderRepositoryWithSpring();
    }
}
