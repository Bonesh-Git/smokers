package com.bnkgroup.dokhan_group.models.repositories;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import com.bnkgroup.dokhan_group.models.entities.People;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class PeopleRepository extends CommonRepository<People, Long> {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    public PeopleRepository() {
        super(People.class);
    }

    public PeopleRepository(Class<People> entityClass) {
        super(entityClass);
    }

    public List<People> findByName(String PersonName) {
        return eM
                .createQuery("SELECT p FROM People p WHERE p.PeopleName = :ln", People.class)
                .setParameter("ln", "%" + PersonName + "%")
                .getResultList();
    }

    public List<People> findPlpList(){
        return eM
                .createQuery(
                        "SELECT p FROM People p WHERE p.peopleId > :ln",
                        People.class)
                .setParameter("ln", 0)
                .getResultList();
    }
}
