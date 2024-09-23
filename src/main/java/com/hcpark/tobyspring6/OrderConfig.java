package com.hcpark.tobyspring6;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hcpark.tobyspring6.data.JdbcOrderRepository;
import com.hcpark.tobyspring6.order.OrderRepository;
import com.hcpark.tobyspring6.order.OrderService;
import com.hcpark.tobyspring6.order.OrderServiceImpl;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderService orderService(
        OrderRepository orderRepository
    ) {
        return new OrderServiceImpl(orderRepository);
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
    public OrderRepository orderRepository(DataSource dataSource) {
        //        return new JpaOrderRepositoryWithSpring();
        return new JdbcOrderRepository(dataSource);
    }
}
