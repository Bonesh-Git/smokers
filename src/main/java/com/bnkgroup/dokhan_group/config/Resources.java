package com.bnkgroup.dokhan_group.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class Resources {

    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UnitDefault");

    @Produces
    public EntityManager produceEntityManager() {
        return emf.createEntityManager();
    }
}

