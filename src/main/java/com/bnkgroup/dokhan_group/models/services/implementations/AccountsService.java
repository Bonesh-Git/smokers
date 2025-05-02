package com.bnkgroup.dokhan_group.models.services.implementations;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import com.bnkgroup.dokhan_group.models.repositories.AccountsRepository;
import com.bnkgroup.dokhan_group.models.services.interfaces.iAccountsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountsService implements iAccountsService {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    @Inject
    private AccountsRepository accountsRepository;

    public AccountsService() {
    }

    public AccountsService(AccountsRepository AcntRepository) {
        this.accountsRepository = AcntRepository;
    }

    @Transactional
    public void makeSampleAccounts() {
/*
        // 2. ایجاد رکورد برای جدول Accounts
        Accounts account1 = new Accounts();
        account1.setAccountType(1); // 1 می‌تواند نوع حساب باشد
        account1.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account1.setPeople(person1);
        account1.setAccountNo("123-456-790-0");
        account1.setAccountUID(UUID.randomUUID());
        accountsRepository.Insert(account1);

        Accounts account2 = new Accounts();
        account2.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account2.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account2.setPeopleUID(person2);
        account2.setAccountNo("123-456-791-0");
        account2.setAccountUID(UUID.randomUUID());
        em.persist(account2);

        Accounts account3 = new Accounts();
        account3.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account3.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account3.setPeopleUID(person3);
        account3.setAccountNo("123-456-792-0");
        account3.setAccountUID(UUID.randomUUID());
        em.persist(account3);

        Accounts account4 = new Accounts();
        account4.setAccountType(1); // 1 می‌تواند نوع حساب باشد
        account4.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account4.setPeopleUID(person3);
        account4.setAccountNo("123-456-793-0");
        account4.setAccountUID(UUID.randomUUID());
        em.persist(account4);

        Accounts account5 = new Accounts();
        account5.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account5.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account5.setPeopleUID(person4);
        account5.setAccountNo("123-456-794-0");
        account5.setAccountUID(UUID.randomUUID());
        em.persist(account5);

        Accounts account6 = new Accounts();
        account6.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account6.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account6.setPeopleUID(person5);
        account6.setAccountNo("123-456-795-0");
        account6.setAccountUID(UUID.randomUUID());
        em.persist(account6);

        Accounts account7 = new Accounts();
        account7.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account7.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account7.setPeopleUID(person5);
        account7.setAccountNo("123-456-796-0");
        account7.setAccountUID(UUID.randomUUID());
        em.persist(account7);

        Accounts account8 = new Accounts();
        account8.setAccountType(2); // 2 می‌تواند نوع حساب باشد
        account8.setOpenDate(OffsetDateTime.now(ZoneId.systemDefault()));
        account8.setPeopleUID(person5);
        account8.setAccountNo("123-456-792-0");
        account8.setAccountUID(UUID.randomUUID());
        em.persist(account8);
*/
    }

    @Override
    @Transactional
    public void Opening(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void Closing(Accounts Acnt) {

    }

    @Override
    public double ReadBalance(Date TransDate) {
        return 0;
    }

    @Override
    public List<Accounts> ReadTrans(Date TransDateFrom, Date TransDateTo) {
        return List.of();
    }

    @Override
    @Transactional
    public void Blocking(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void JoinToCard(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void InternalTransferTo(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void PayaTransferTo(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void SatnaTransferTo(Accounts Acnt) {

    }

    @Override
    @Transactional
    public void InterestApplying(Date TransDateTo) {

    }

    @Override
    public List<Accounts> AccountInfo() {
        return List.of();
    }

    @Override
    public List<Accounts> find() {

        return this.accountsRepository.findAll();
        //return this.accountsRepository.findAccList();

    }
}
