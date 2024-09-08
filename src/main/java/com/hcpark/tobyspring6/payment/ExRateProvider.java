package com.hcpark.tobyspring6.payment;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExchangeRate(String currency);
}
