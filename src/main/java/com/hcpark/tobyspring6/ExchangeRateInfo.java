package com.hcpark.tobyspring6;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRateInfo(String result, Map<String, BigDecimal> rates) {
}
