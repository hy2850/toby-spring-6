package com.hcpark.tobyspring6.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal total);

    List<Order> createOrders(List<OrderReq> orderReqs);

    Order getOrder(Long id);
}
