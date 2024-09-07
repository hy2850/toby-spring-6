package com.hcpark.tobyspring6.payment;

import java.io.IOException;
import java.math.BigDecimal;

public class ExRateProviderStub implements ExRateProvider {

    BigDecimal rate;

    public ExRateProviderStub(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        return rate;
    }
}
