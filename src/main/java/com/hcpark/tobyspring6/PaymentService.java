package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentService {

    static String TARGET_CURRENCY = "KRW";

    public Payment prepare(Long orderId, String currency, BigDecimal payAmount) throws IOException {
        var exchangeRate = getExchangeRate(currency);

        // 환율 계산
        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }

    abstract BigDecimal getExchangeRate(String currency) throws IOException;
}
