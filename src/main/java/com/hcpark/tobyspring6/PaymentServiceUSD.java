package com.hcpark.tobyspring6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentServiceUSD extends PaymentService {

    @Override
    BigDecimal getExchangeRate(String currency) throws IOException {
        var url = new URL("https://open.er-api.com/v6/latest/" + currency);
        var connection = (HttpURLConnection) url.openConnection();
        var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        var response = br.lines().collect(Collectors.joining());

        var objectMapper = new ObjectMapper();
        var exchangeRateInfo = objectMapper.readValue(response, ExchangeRateInfo.class);
        return exchangeRateInfo.rates().get(TARGET_CURRENCY);
    }
}
