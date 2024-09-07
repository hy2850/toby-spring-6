package com.hcpark.tobyspring6;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hcpark.tobyspring6.payment.ExRateProvider;
import com.hcpark.tobyspring6.exrate.ExRateProviderStub;
import com.hcpark.tobyspring6.payment.PaymentService;

@Configuration
public class TestObjectFactory {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(getExRateProviderStub());
    }

    @Bean
    public ExRateProvider getExRateProviderStub() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }
}
