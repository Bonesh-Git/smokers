package com.bonesh.paya.model.repository;

import com.bonesh.paya.model.entity.Customer;
import com.bonesh.paya.model.entity.Deposit;
import com.bonesh.paya.model.etc.EntityManagerProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class DepositRepository {
    public void save(Deposit deposit) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(deposit);
        transaction.commit();
        entityManager.close();

    }    public void update() {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.flush();
        transaction.commit();
        entityManager.close();

    }

    public Optional<Deposit> findByShabaNumber(long shabaNumber) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        Optional<Deposit> deposit = Optional.ofNullable(entityManager.find(Deposit.class, shabaNumber));
        entityManager.close();
        return deposit;
    }
}
