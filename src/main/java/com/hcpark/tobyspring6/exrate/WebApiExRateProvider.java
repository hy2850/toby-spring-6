package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;

import com.hcpark.tobyspring6.api.ApiTemplate;
import com.hcpark.tobyspring6.api.ErApiExRateExtractor;
import com.hcpark.tobyspring6.api.SimpleApiExecutor;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
        return ApiTemplate.getExchangeRateWithApi(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }
}
