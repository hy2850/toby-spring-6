package com.hcpark.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

import com.hcpark.tobyspring6.data.JpaOrderRepositoryWithSpring;
import com.hcpark.tobyspring6.order.OrderRepository;
import com.hcpark.tobyspring6.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }

//    // EntityManagerFactory 빈을 인자로 주입받음
//    @Bean
//    public JpaOrderRepository orderRepository(EntityManagerFactory emf) {
//        return new JpaOrderRepository(emf);
//    }
//
//    @Bean
//    public JpaOrderRepositoryWithSpring orderRepositoryWithSpring() {
//        return new JpaOrderRepositoryWithSpring();
//    }

    @Bean
    public OrderRepository orderRepository() {
        return new JpaOrderRepositoryWithSpring();
    }
}
