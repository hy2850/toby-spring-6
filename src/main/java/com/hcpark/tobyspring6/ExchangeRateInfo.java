package com.hcpark.tobyspring6;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateInfo(String result, Map<String, BigDecimal> rates) {
}
