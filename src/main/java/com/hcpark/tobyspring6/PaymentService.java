package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService() {
        // 구체에 강하게 의존 -> 사용하고 싶은 구체를 바꾸고 싶다면 여기 코드를 바꿔야; 확장성 떨어짐
//        this.exRateProvider = new WebApiExRateProvider();
        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal payAmount) throws IOException {
        var exchangeRate = exRateProvider.getExchangeRate(currency);

        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }
}
