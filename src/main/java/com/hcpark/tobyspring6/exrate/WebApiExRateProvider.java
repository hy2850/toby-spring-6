package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;

import com.hcpark.tobyspring6.api.ApiTemplate;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
//        return apiTemplate.getExchangeRateWithApi(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
//        return apiTemplate.runApiForExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
        return apiTemplate.runApiForExRate(url);
    }
}
