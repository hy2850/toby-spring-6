package com.hcpark.tobyspring6.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Payment 도메인 객체 내부의 비즈니스 로직을 테스트
public class PaymentTest {

    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("Payment 도메인 객체 내부의 비즈니스 로직 테스트")
    void testConvertedAmount() {
        // given
        var payAmount = BigDecimal.TEN;
        var exchangeRate = BigDecimal.valueOf(1_000);

        // when
        var payment = Payment.createPayment(1L, "USD", payAmount, exchangeRate, this.clock);

        // then
        Assertions.assertThat(payment.convertedPayAmount()).isEqualByComparingTo(payAmount.multiply(exchangeRate));
        Assertions.assertThat(payment.validUntil()).isEqualTo(LocalDateTime.now(this.clock).plusMinutes(30));
    }
}
