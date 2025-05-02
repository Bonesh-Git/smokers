package com.bnkgroup.dokhan_group.models.services.implementations;

import com.bnkgroup.dokhan_group.models.entities.People;
import com.bnkgroup.dokhan_group.models.repositories.PeopleRepository;
import com.bnkgroup.dokhan_group.models.services.interfaces.iPeopleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PeopleService implements iPeopleService {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    @Inject
    private PeopleRepository peopleRepository;

    public PeopleService() {
    }

    public PeopleService(PeopleRepository prsnRepository) {
        this.peopleRepository = prsnRepository;
    }

    @Transactional
    public void makeSamplePeople() {
        // 1. ایجاد رکورد برای جدول People
        People person1 = new People();
        person1.setPeopleUID(UUID.randomUUID());
        person1.setPeopleId(333);
        person1.setPeopleName("علی*|*علوی");
        person1.setPeopleIsActive(true);
        person1.setPeopleStatus((short) 1);
        peopleRepository.Insert(person1);
/*
            People person2 = new People();
            person2.setPeopleUID(UUID.randomUUID());
            person2.setPeopleName("رضا*|*رضوی");
            person2.setPeopleIsActive(true);
            person2.setPeopleStatus((short) 1);
            em.persist(person2);

            People person3 = new People();
            person3.setPeopleUID(UUID.randomUUID());
            person3.setPeopleName("تینا*|*ایرانی");
            person3.setPeopleIsActive(true);
            person3.setPeopleStatus((short) 1);
            em.persist(person3);

            People person4 = new People();
            person4.setPeopleUID(UUID.randomUUID());
            person4.setPeopleName("اسرا*|*آسمانی");
            person4.setPeopleIsActive(true);
            person4.setPeopleStatus((short) 1);
            em.persist(person4);

            People person5 = new People();
            person5.setPeopleUID(UUID.randomUUID());
            person5.setPeopleName("روکسانا*|*بهشتی");
            person5.setPeopleIsActive(true);
            person5.setPeopleStatus((short) 1);
            em.persist(person5);
*/
    }

    @Override
    @Transactional
    public void Register(People prsn) {
        peopleRepository.Insert(prsn);
    }

    @Override
    @Transactional
    public void Updating(People prsn) {

    }

    @Override
    public boolean InquerySabtahval(Date TransDate) {
        return false;
    }

    @Override
    public boolean InqueryForeign(Date TransDate) {
        return false;
    }

    @Override
    public boolean InqueryEnterprise(Date TransDate) {
        return false;
    }

    @Override
    public boolean InqueryBadRecord(Date TransDate) {
        return false;
    }

    @Override
    @Transactional
    public void BookEnding(People prsn) {

    }

    @Override
    @Transactional
    public void JoinToCard(People prsn) {

    }

    @Override
    @Transactional
    public void BlackListExternal(People prsn) {

    }

    @Override
    @Transactional
    public void BlackListInternal(People prsn) {

    }

    @Override
    @Transactional
    public void Ticketting(People prsn) {

    }

    @Override
    @Transactional
    public void MessagingTo(People prsn) {

    }

    @Override
    public List<People> find() {
        return this.peopleRepository.findAll();
        //return this.peopleRepository.findPlpList();
    }

    @Override
    public List<People> findById(Long id) {
        return List.of();
    }

    @Override
    public List<People> findByName(String personName) {
        return List.of();
    }
}
