package com.hcpark.tobyspring6.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

//@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal payAmount) throws IOException {
        var exchangeRate = exRateProvider.getExchangeRate(currency);

        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now(this.clock).plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }
}
