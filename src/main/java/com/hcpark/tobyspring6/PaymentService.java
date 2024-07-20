package com.hcpark.tobyspring6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public static Payment prepare(Long orderId, String currency, BigDecimal payAmount) throws IOException {
        // 환율 정보 API 호출
        // https://open.er-api.com/v6/latest/USD
        var url = new URL("https://open.er-api.com/v6/latest/" + currency);
        var connection = (HttpURLConnection) url.openConnection();
        var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        var response = br.lines().collect(Collectors.joining());

        System.out.println(response);

        // 환율 정보 JSON 변환

        // 환율 계산

        return new Payment(orderId, currency, payAmount, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());
    }

    public static void main(String[] args) throws IOException {
        var payment = PaymentService.prepare(123L, "USD", BigDecimal.valueOf(100));
        System.out.println(payment);
    }
}
