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
    }
}