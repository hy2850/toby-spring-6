package com.hcpark.tobyspring6.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hcpark.tobyspring6.TestObjectFactory;

class PaymentServiceSpringTest {

    @Test
    @DisplayName("스프링 테스트 컨테이너 활용한 PaymentService 테스트")
    void prepare() throws IOException {
        // given
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestObjectFactory.class);

        // when
        var paymentService = beanFactory.getBean(PaymentService.class);
        var payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 1. 환율 정보 가져오기
        Assertions.assertThat(payment.exchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));

        // 2. 원화 환산 금액 맞는지
        Assertions.assertThat(payment.convertedPayAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

        // 3. 원화 환산 금액의 유효시간 유효한지
        Assertions.assertThat(payment.validUntil()).isAfter(LocalDateTime.now());
        Assertions.assertThat(payment.validUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }
}