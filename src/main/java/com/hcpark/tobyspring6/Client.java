package com.hcpark.tobyspring6;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        var paymentService = new PaymentServiceUSD();
        var payment = paymentService.prepare(123L, "USD", BigDecimal.valueOf(100));
        System.out.println(payment);
    }
}
