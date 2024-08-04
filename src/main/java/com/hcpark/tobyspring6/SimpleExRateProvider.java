package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;

//@Component
public class SimpleExRateProvider implements  ExRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) throws IOException {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1200L);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다: " + currency);
    }
}
