package com.bonesh.paya.model.repository;

import com.bonesh.paya.model.entity.Customer;
import com.bonesh.paya.model.etc.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class CustomerRepository {
    public void save(Customer customer) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();

    }

    public Optional<Customer> findById(int id) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        Optional<Customer> customer = Optional.ofNullable(entityManager.find(Customer.class, id));
        entityManager.close();
        return customer;
    }

}
