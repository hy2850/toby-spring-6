package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final WebApiExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new WebApiExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal payAmount) throws IOException {
//        var exRateProvider = new WebApiExRateProvider();
        var exchangeRate = exRateProvider.getWebExchangeRate(currency);

//        var exRateProvider = new SimpleExRateProvider();
//        var exchangeRate = exRateProvider.getSimpleExchangeRate(currency);

        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }
}
