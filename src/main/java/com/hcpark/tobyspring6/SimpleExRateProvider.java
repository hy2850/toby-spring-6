package com.hcpark.tobyspring6;

import java.math.BigDecimal;

public class SimpleExRateProvider {

    BigDecimal getSimpleExchangeRate(String currency) {
        if(currency.equals("USD")) {
            return BigDecimal.valueOf(1200L);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다: " + currency);
    }
}
