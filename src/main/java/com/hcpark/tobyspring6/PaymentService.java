package com.hcpark.tobyspring6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentService {

    private Long orderId;
    private String currency;
    private BigDecimal payAmount;

    private static String TARGET_CURRENCY = "KRW";

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

    private BigDecimal getExchangeRate() throws IOException {
        // 환율 정보 API 호출
        var url = new URL("https://open.er-api.com/v6/latest/" + currency);
        var connection = (HttpURLConnection) url.openConnection();
        var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        var response = br.lines().collect(Collectors.joining());

        // 환율 정보 JSON 변환
        var objectMapper = new ObjectMapper();
        var exchangeRateInfo = objectMapper.readValue(response, ExchangeRateInfo.class);
        return exchangeRateInfo.rates().get(TARGET_CURRENCY);
    }

    public static void main(String[] args) throws IOException {
        var paymentService = new PaymentService(123L, "USD", BigDecimal.valueOf(100));
        var payment = paymentService.prepare();
        System.out.println(payment);
    }
}
