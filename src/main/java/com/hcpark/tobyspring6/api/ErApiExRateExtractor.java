package com.hcpark.tobyspring6.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcpark.tobyspring6.exrate.ExchangeRateInfo;

public class ErApiExRateExtractor implements ExRateExtractor {

    @Override
    public ExchangeRateInfo extractExRateFrom(String response) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(response, ExchangeRateInfo.class);
    }
}
