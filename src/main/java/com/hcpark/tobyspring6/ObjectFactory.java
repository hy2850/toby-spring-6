package com.hcpark.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan
public class ObjectFactory {

    @Bean
    public PaymentService getPaymentService() {
        return new PaymentService(getCachedExRateProvider());
    }

    @Bean
    private static ExRateProvider getCachedExRateProvider() {
        return new CachedExRateProvider(getExRateProvider());
    }

    @Bean
    private static ExRateProvider getExRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }
}
