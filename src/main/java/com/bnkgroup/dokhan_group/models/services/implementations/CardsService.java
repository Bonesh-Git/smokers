package com.bnkgroup.dokhan_group.models.services.implementations;

import com.bnkgroup.dokhan_group.models.entities.Cards;
import com.bnkgroup.dokhan_group.models.repositories.CardsRepository;
import com.bnkgroup.dokhan_group.models.services.interfaces.iCardsService;
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
public class CardsService implements iCardsService {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    @Inject
    private CardsRepository cardsRepository;

    public CardsService() {
    }

    public CardsService(CardsRepository crdRepository) {
        this.cardsRepository = crdRepository;
    }

    @Transactional
    public void makeSampleCards() {
 /*
        // 3. ایجاد رکورد برای جدول Cards
        Cards card1 = new Cards();
        card1.setAccounts(account3);
        card1.setCardUID(UUID.randomUUID());
        card1.setCardNo("1234-5678-9011-1213");
        card1.setCardsIsActive(true);
        card1.setCardsStatus(1);  // 1 می‌تواند وضعیت کارت باشد
        card1.setDatetimeCrt(OffsetDateTime.now(ZoneId.systemDefault()));
        cardsRepository.Insert(card1);

        Cards card2 = new Cards();
        card2.setAccountUID(account4);
        card2.setCardUID(UUID.randomUUID());
        card2.setCardNo("1234-5678-9011-1214");
        card2.setCardsIsActive(true);
        card2.setCardsStatus(1);
        card2.setDatetimeCrt(OffsetDateTime.now(ZoneId.systemDefault()));
        em.persist(card2);

  */
    }

    @Override
    @Transactional
    public void Issuing(Cards crd) {

    }

    @Override
    @Transactional
    public void Terminating(Cards crd) {

    }

    @Override
    @Transactional
    public void ActivateStatus(Cards crd) {

    }

    @Override
    @Transactional
    public void Blocking(Cards crd) {

    }

    @Override
    @Transactional
    public void ChangeKey1(Cards crd) {

    }

    @Override
    public List<Cards> CardInfo(Cards crd) {
        return List.of();
    }

    @Override
    @Transactional
    public void ChangeKey2(Cards crd) {

    }

    @Override
    @Transactional
    public void PooyaKey2Status(Cards crd) {

    }

    @Override
    public double ReadBalance(Date TransDate) {
        return 0;
    }

    @Override
    public List<CardsRepository> ReadTrans(Date TransDateFrom, Date TransDateTo) {
        return List.of();
    }

    @Override
    @Transactional
    public void JoinToAccount(Cards crd) {

    }

    @Override
    @Transactional
    public void ShetabTransferTo(Cards crd) {

    }

    @Override
    public List<Cards> find() {
        return this.cardsRepository.findAll();
    }

}
