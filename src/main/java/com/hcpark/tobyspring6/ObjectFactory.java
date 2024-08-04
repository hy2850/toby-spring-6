package com.hcpark.tobyspring6;

public class ObjectFactory {

    public PaymentService getPaymentService() {
        return new PaymentService(getExRateProvider());
    }

    private static ExRateProvider getExRateProvider() {
//        return new SimpleExRateProvider();
        return new WebApiExRateProvider();
    }
}
