package com.hcpark.tobyspring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hcpark.tobyspring6.exrate.ExchangeRateInfo;

public interface ExRateExtractor {

    ExchangeRateInfo extractExRateFrom(String response) throws JsonProcessingException;
}
