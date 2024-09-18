package com.hcpark.tobyspring6.exrate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static String TARGET_CURRENCY = "KRW";

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
        return getExchangeRateWithApi(url);
    }

    private static BigDecimal getExchangeRateWithApi(String url) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = executeApi(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExchangeRateInfo exchangeRateInfo;
        try {
            exchangeRateInfo = extractExRateFrom(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return exchangeRateInfo.rates().get(TARGET_CURRENCY);
    }

    private static String executeApi(URI uri) throws IOException {
        String response;
        var connection = (HttpURLConnection) uri.toURL().openConnection();
        var br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = br.lines().collect(Collectors.joining());
        return response;
    }

    private static ExchangeRateInfo extractExRateFrom(String response) throws JsonProcessingException {
        ExchangeRateInfo exchangeRateInfo;
        var objectMapper = new ObjectMapper();
        exchangeRateInfo = objectMapper.readValue(response, ExchangeRateInfo.class);
        return exchangeRateInfo;
    }
}
