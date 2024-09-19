package com.hcpark.tobyspring6.order;

import javax.naming.OperationNotSupportedException;

public interface OrderRepository {

    Order save(Order order);

    default Order find(Long id) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("지원하지 않는 기능입니다.");
    }
}
