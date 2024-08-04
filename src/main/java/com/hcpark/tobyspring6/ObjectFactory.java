package com.hcpark.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(getExRateProvider());
    }

    @Bean
    private static ExRateProvider getExRateProvider() {
        return new SimpleExRateProvider();
//        return new WebApiExRateProvider();
    }
}
