package com.hcpark.tobyspring6.exrate;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.hcpark.tobyspring6.api.ApiTemplate;
import com.hcpark.tobyspring6.api.ErApiExRateExtractor;
import com.hcpark.tobyspring6.payment.ExRateProvider;

//@Component
public class WebApiExRateProvider implements ExRateProvider {

    ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExchangeRate(String currency) {
        var url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.getExchangeRateWithApi(url, uri -> {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

            try (HttpClient client = HttpClient.newHttpClient()) {
                return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, new ErApiExRateExtractor());
    }
}
