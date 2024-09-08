package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;

import com.hcpark.tobyspring6.payment.ExRateProvider;

public class ExRateProviderStub implements ExRateProvider {

    BigDecimal rate;

    public ExRateProviderStub(BigDecimal rate) {
        this.rate = rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) {
        return rate;
    }
}
