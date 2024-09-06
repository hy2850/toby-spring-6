package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        return target.getExchangeRate(currency);
    }
}
