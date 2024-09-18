package com.hcpark.tobyspring6.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcpark.tobyspring6.exrate.ExchangeRateInfo;

public class ApiTemplate {

    private static String TARGET_CURRENCY = "KRW";

    public BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
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
