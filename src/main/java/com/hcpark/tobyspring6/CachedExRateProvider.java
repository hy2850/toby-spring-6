package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExchangeRate;
    private LocalDateTime cacheExpireDateTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        if (Objects.isNull(cachedExchangeRate) || cacheExpireDateTime.isBefore(LocalDateTime.now())) {
            cachedExchangeRate = target.getExchangeRate(currency);
            cacheExpireDateTime = LocalDateTime.now().plusSeconds(3); // 3초 timeout

            System.out.println("=== Cache updated ===");
            System.out.println("new exRate: " + cachedExchangeRate);

            return cachedExchangeRate;
        }

        return target.getExchangeRate(currency);
    }
}
