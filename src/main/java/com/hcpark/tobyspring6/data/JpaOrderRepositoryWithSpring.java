package com.hcpark.tobyspring6.data;

import com.hcpark.tobyspring6.order.Order;
import com.hcpark.tobyspring6.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepositoryWithSpring implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Order save(Order order) {
        entityManager.persist(order);
        return order;
    }
}
