package com.hcpark.tobyspring6.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcpark.tobyspring6.exrate.ExchangeRateInfo;

public class ApiTemplate {

    private static String TARGET_CURRENCY = "KRW";

    private final ApiExecutor defaultApiExecutor;
    private final ExRateExtractor defaultExRateExtractor;

    public ApiTemplate(ApiExecutor defaultApiExecutor, ExRateExtractor defaultExRateExtractor) {
        this.defaultApiExecutor = defaultApiExecutor;
        this.defaultExRateExtractor = defaultExRateExtractor;
    }

    public ApiTemplate() {
        this.defaultApiExecutor = new HttpClientApiExecutor();
        this.defaultExRateExtractor = new ErApiExRateExtractor();
    }

    public BigDecimal runApiForExRate(String url) {
        return runApiForExRate(url, this.defaultApiExecutor, this.defaultExRateExtractor);
    }

    public BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor) {
        return runApiForExRate(url, apiExecutor, this.defaultExRateExtractor);
    }

    public BigDecimal runApiForExRate(String url, ExRateExtractor exRateExtractor) {
        return runApiForExRate(url, this.defaultApiExecutor, exRateExtractor);
    }

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
