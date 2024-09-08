package com.hcpark.tobyspring6.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public record Payment(Long orderId, String currency, BigDecimal payAmount, BigDecimal exchangeRate, BigDecimal convertedPayAmount, LocalDateTime validUntil) {

    public static Payment createPayment(Long orderId, String currency, BigDecimal payAmount, BigDecimal exchangeRate, Clock clock) {
        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now(clock).plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }
}
