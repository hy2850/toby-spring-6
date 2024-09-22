package com.hcpark.tobyspring6.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcpark.tobyspring6.OrderConfig;

// 스프링 컨테이너와 자동 DI를 이용
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DataSource dataSource;

    @Test
    void createOrder() {
        var savedOrder = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(savedOrder.getId()).isGreaterThan(0);

        var savedOrder2 = orderService.createOrder("101", BigDecimal.TEN);
        assertThat(savedOrder2.getId()).isEqualTo(savedOrder.getId() + 1);
    }

    @Test
    void createOrders() {
        // given
        var orderReqs = List.of(
            new OrderReq("100", BigDecimal.TEN),
            new OrderReq("101", BigDecimal.TEN)
        );

        // when
        var orders = orderService.createOrders(orderReqs);

        // then
        assertThat(orders.size()).isEqualTo(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createOrders_duplicate() {
        // given
        var no = "200";

        var orderReqs = List.of(
            new OrderReq(no, BigDecimal.TEN),
            new OrderReq(no, BigDecimal.TEN)
        );

        // when, then
        assertThatThrownBy(() -> orderService.createOrders(orderReqs)).isInstanceOf(DataIntegrityViolationException.class);

        // then
        var jdbcClient = JdbcClient.create(dataSource);
        var count = jdbcClient.sql("select count(*) from orders")
            .query(Long.class)
            .single();
        assertThat(count).isEqualTo(0);
    }

    @Test
    void find() {
        // given
        var savedOrder = orderService.createOrder("102", BigDecimal.TEN);

        // when
        var foundOrder = orderService.getOrder(savedOrder.getId());

        // then
        assertThat(foundOrder.getId()).isEqualTo(savedOrder.getId());
        assertThat(foundOrder.getNo()).isEqualTo(savedOrder.getNo());
        assertThat(foundOrder.getTotal()).isEqualByComparingTo(savedOrder.getTotal());
    }
}