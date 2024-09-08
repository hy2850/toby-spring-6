package com.hcpark.tobyspring6;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hcpark.tobyspring6.payment.ExRateProvider;
import com.hcpark.tobyspring6.exrate.ExRateProviderStub;
import com.hcpark.tobyspring6.payment.PaymentService;

@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(getExRateProviderStub(), getClock());
    }

    @Bean
    public ExRateProvider getExRateProviderStub() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock getClock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
