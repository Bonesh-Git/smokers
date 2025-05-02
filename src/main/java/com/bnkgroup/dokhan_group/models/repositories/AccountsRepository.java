package com.bnkgroup.dokhan_group.models.repositories;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class AccountsRepository extends CommonRepository<Accounts, Long> {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    public AccountsRepository() {
        super(Accounts.class);
    }

    public AccountsRepository(Class<Accounts> entityClass) {
        super(entityClass);
    }

    public List<Accounts> findByName(String PersonName) {
        return eM
                .createQuery(
                        "SELECT a FROM Accounts a JOIN a.people p WHERE p.PeopleName = :ln",
                        Accounts.class)
                .setParameter("ln", PersonName)
                .getResultList();
    }

    public List<Accounts> findAccList(){
        return eM
                .createQuery(
                        "SELECT a FROM Accounts a JOIN a.people p WHERE p.peopleId > :ln",
                        Accounts.class)
                .setParameter("ln", 0)
                .getResultList();
    }
}
