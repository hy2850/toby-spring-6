package com.hcpark.tobyspring6.payment;

import java.io.IOException;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcpark.tobyspring6.TestObjectFactory;
import com.hcpark.tobyspring6.exrate.ExRateProviderStub;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=TestObjectFactory.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ExRateProviderStub exRateProviderStub;

    @Test
    @DisplayName("스프링 테스트 컨테이너 활용한 PaymentService 테스트")
    void prepare() throws IOException {
        // given

        // when
        var payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 1. 환율 정보 가져오기
        Assertions.assertThat(payment.exchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1_000));

        // 2. 원화 환산 금액 맞는지
        Assertions.assertThat(payment.convertedPayAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));


        // given
        exRateProviderStub.setRate(BigDecimal.valueOf(5_000));

        // when
        payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 1. 환율 정보 가져오기
        Assertions.assertThat(payment.exchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(5_000));

        // 2. 원화 환산 금액 맞는지
        Assertions.assertThat(payment.convertedPayAmount()).isEqualByComparingTo(BigDecimal.valueOf(50_000));
    }
}