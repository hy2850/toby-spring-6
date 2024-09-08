package com.hcpark.tobyspring6.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

// Payment 기본 constructor를 private으로 만들고, createPayment 객체로만 생성할 수 있게 하는게 맞다. 리팩토링 귀찮아서 스킵
public record Payment(Long orderId, String currency, BigDecimal payAmount, BigDecimal exchangeRate, BigDecimal convertedPayAmount, LocalDateTime validUntil) {

    public static Payment createPayment(Long orderId, String currency, BigDecimal payAmount, BigDecimal exchangeRate, Clock clock) {
        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }

    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(validUntil);
    }
}
