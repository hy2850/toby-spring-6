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
import com.hcpark.tobyspring6.api.ApiExecutor;
import com.hcpark.tobyspring6.api.ExRateExtractor;
import com.hcpark.tobyspring6.api.SimpleApiExecutor;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private static String TARGET_CURRENCY = "KRW";

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;

        return getExchangeRateWithApi(url, new SimpleApiExecutor(), new ExRateExtractor() {
            @Override
            public ExchangeRateInfo extractExRateFrom(String response) throws JsonProcessingException {
                var objectMapper = new ObjectMapper();
                return objectMapper.readValue(response, ExchangeRateInfo.class);
            }
        });
    }

    private static BigDecimal getExchangeRateWithApi(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.executeApi(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ExchangeRateInfo exchangeRateInfo;
        try {
            exchangeRateInfo = exRateExtractor.extractExRateFrom(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return exchangeRateInfo.rates().get(TARGET_CURRENCY);
    }
}
