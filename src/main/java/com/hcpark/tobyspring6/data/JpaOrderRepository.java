package com.hcpark.tobyspring6.data;

import com.hcpark.tobyspring6.order.Order;
import com.hcpark.tobyspring6.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class JpaOrderRepository implements OrderRepository {

    private final EntityManagerFactory emf;

    public JpaOrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Order save(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        try {
           System.out.println(order);
           em.persist(order);
           System.out.println(order);

           transaction.commit();
        }
        catch (RuntimeException e) {
           if(transaction.isActive()) transaction.rollback();
           throw e;
        }

        if(em.isOpen()) em.close();

        return order;
   }
}
