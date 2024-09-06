package com.hcpark.tobyspring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hcpark.tobyspring6.exrate.CachedExRateProvider;
import com.hcpark.tobyspring6.payment.ExRateProvider;
import com.hcpark.tobyspring6.exrate.WebApiExRateProvider;
import com.hcpark.tobyspring6.payment.PaymentService;

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
