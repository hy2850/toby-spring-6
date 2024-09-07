package com.hcpark.tobyspring6.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hcpark.tobyspring6.exrate.ExRateProviderStub;
import com.hcpark.tobyspring6.exrate.WebApiExRateProvider;

class PaymentServiceTest {

    @Test
    @DisplayName("PaymentService 비즈니스 로직 검증")
    void prepare() throws IOException {
        // given
        var paymentService = new PaymentService(new WebApiExRateProvider());

        // when
        var payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 1. 환율 정보 가져오기
        Assertions.assertThat(payment.exchangeRate()).isNotNull();

        // 2. 원화 환산 금액 맞는지
        Assertions.assertThat(payment.convertedPayAmount())
            .isEqualByComparingTo(payment.exchangeRate().multiply(payment.payAmount()));

        // 3. 원화 환산 금액의 유효시간 유효한지
        Assertions.assertThat(payment.validUntil()).isAfter(LocalDateTime.now());
        Assertions.assertThat(payment.validUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    @DisplayName("ExRateProviderStub 사용해서 PaymentService 비즈니스 로직 검증")
    void prepareWithStub() throws IOException {
        testConvertedAmount(BigDecimal.valueOf(100), BigDecimal.valueOf(1_000));
        testConvertedAmount(BigDecimal.valueOf(300), BigDecimal.valueOf(3_000));
        testConvertedAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
    }

    private void testConvertedAmount(BigDecimal exRateInput, BigDecimal convertedAmountToTest) throws IOException {
        // given
        var paymentService = new PaymentService(new ExRateProviderStub(exRateInput));

        // when
        var payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // then
        // 1. 환율 정보 가져오기
        Assertions.assertThat(payment.exchangeRate()).isEqualByComparingTo(exRateInput);

        // 2. 원화 환산 금액 맞는지
        Assertions.assertThat(payment.convertedPayAmount()).isEqualByComparingTo(convertedAmountToTest);
    }
}