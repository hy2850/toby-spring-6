package com.hcpark.tobyspring6;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hcpark.tobyspring6.api.ApiTemplate;
import com.hcpark.tobyspring6.api.ErApiExRateExtractor;
import com.hcpark.tobyspring6.api.HttpClientApiExecutor;
import com.hcpark.tobyspring6.exrate.CachedExRateProvider;
import com.hcpark.tobyspring6.payment.ExRateProvider;
import com.hcpark.tobyspring6.exrate.WebApiExRateProvider;
import com.hcpark.tobyspring6.payment.PaymentService;

@Configuration
//@ComponentScan
public class PaymentConfig {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(getCachedExRateProvider(), getClock());
    }

    @Bean
    public ExRateProvider getCachedExRateProvider() {
        return new CachedExRateProvider(getExRateProvider());
    }

    @Bean
    public ExRateProvider getExRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider(getApiTemplate());
    }

    @Bean
    public ApiTemplate getApiTemplate() {
        return new ApiTemplate(new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }
}
