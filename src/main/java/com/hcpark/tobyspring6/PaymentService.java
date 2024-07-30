package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentService {

    Long orderId;
    String currency;
    BigDecimal payAmount;

    static String TARGET_CURRENCY = "KRW";

    public PaymentService(Long orderId, String currency, BigDecimal payAmount) {
        this.orderId = orderId;
        this.currency = currency;
        this.payAmount = payAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    @Override
    public String toString() {
        return "PaymentService{" +
            "orderId=" + orderId +
            ", currency='" + currency + '\'' +
            ", payAmount=" + payAmount +
            '}';
    }

    public Payment prepare() throws IOException {
        var exchangeRate = getExchangeRate();

        // 환율 계산
        var convertedPayAmount = payAmount.multiply(exchangeRate);
        var validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, payAmount, exchangeRate, convertedPayAmount, validUntil);
    }

    abstract BigDecimal getExchangeRate() throws IOException;
}
