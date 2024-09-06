package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExchangeRate;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        if (Objects.isNull(cachedExchangeRate)) {
            cachedExchangeRate = target.getExchangeRate(currency);
            return cachedExchangeRate;
        }

        return target.getExchangeRate(currency);
    }
}
