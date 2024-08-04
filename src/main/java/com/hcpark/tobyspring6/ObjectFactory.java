package com.hcpark.tobyspring6;

public class ObjectFactory {

    public PaymentService getPaymentService() {
        return new PaymentService(new WebApiExRateProvider());
    }
}
