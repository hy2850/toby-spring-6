package com.hcpark.tobyspring6.order;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcpark.tobyspring6.OrderConfig;

// 스프링 컨테이너와 자동 DI를 이용
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    OrderService orderService;

    @Test
    void save() {
        var savedOrder = orderService.createOrder("100", BigDecimal.TEN);
        assertThat(savedOrder.getId()).isGreaterThan(0);

        var savedOrder2 = orderService.createOrder("101", BigDecimal.TEN);
        assertThat(savedOrder2.getId()).isEqualTo(savedOrder.getId() + 1);
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