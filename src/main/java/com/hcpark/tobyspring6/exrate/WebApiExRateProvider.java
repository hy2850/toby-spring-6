package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;

import com.hcpark.tobyspring6.api.ApiTemplate;
import com.hcpark.tobyspring6.api.ErApiExRateExtractor;
import com.hcpark.tobyspring6.api.HttpClientApiExecutor;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
//        return apiTemplate.getExchangeRateWithApi(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
//        return apiTemplate.runApiForExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
        return apiTemplate.runApiForExRate(url);
    }
}
