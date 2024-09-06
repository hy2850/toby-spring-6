package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);

//        var paymentService = beanFactory.getBean(PaymentService.class);
//        var paymentService2 = beanFactory.getBean(PaymentService.class);
//        System.out.println(paymentService == paymentService2); // true

        var objectFactory = beanFactory.getBean(ObjectFactory.class);
        var paymentService = objectFactory.getPaymentService();
        var paymentService2 = objectFactory.getPaymentService();
        System.out.println(paymentService == paymentService2); // true

        var payment1 = paymentService.prepare(123L, "USD", BigDecimal.valueOf(100));
        System.out.println(payment1);

        TimeUnit.SECONDS.sleep(1);

        var payment2 = paymentService.prepare(123L, "USD", BigDecimal.valueOf(100));
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(3);

        var payment3 = paymentService.prepare(123L, "USD", BigDecimal.valueOf(100));
        System.out.println(payment3);
    }
}
