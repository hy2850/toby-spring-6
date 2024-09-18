package com.hcpark.tobyspring6.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class OrderRepository {

    private final EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
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
