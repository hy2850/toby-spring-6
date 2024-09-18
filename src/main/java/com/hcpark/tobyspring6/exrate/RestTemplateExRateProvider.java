package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.hcpark.tobyspring6.payment.ExRateProvider;

public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
        return this.restTemplate.getForObject(url, ExchangeRateInfo.class).rates().get("KRW");
    }
}
