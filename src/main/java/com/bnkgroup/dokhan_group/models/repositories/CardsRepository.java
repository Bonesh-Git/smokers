package com.bnkgroup.dokhan_group.models.repositories;

import com.bnkgroup.dokhan_group.models.entities.Cards;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CardsRepository extends CommonRepository<Cards, Long> {

    //@PersistenceContext(unitName = "UnitDefault")
    @Inject
    private EntityManager eM;

    public CardsRepository() {
        super(Cards.class);
    }

    public CardsRepository(Class<Cards> entityClass) {
        super(entityClass);
    }

    public List<Cards> findByName(String PersonName) {
        return eM
                .createQuery("""
                            SELECT a FROM Cards c 
                            JOIN c.accounts a 
                            JOIN a.people p 
                            WHERE p.PeopleName = :ln
                            """, Cards.class)
                .setParameter("ln", "%" + PersonName + "%")
                .getResultList();
    }

    // حذف کارت بر اساس CardUID
    public void Delete(UUID cardUID) {
        Cards card = eM.find(Cards.class, cardUID);
        if (card != null) {
            eM.remove(card);
        }
    }

    // پیدا کردن کارت بر اساس CardUID
    public Optional<Cards> findById(UUID cardUID) {
        Cards card = eM.find(Cards.class, cardUID);
        return Optional.ofNullable(card);
    }

    // پیدا کردن کارت بر اساس شماره کارت
    public Optional<Cards> findByCardNo(String cardNo) {
        TypedQuery<Cards> query = eM.createQuery(
                "SELECT c FROM Cards c WHERE c.cardNo = :cardNo", Cards.class);
        query.setParameter("cardNo", "%" + cardNo + "%");
        Cards card = query.getResultList().stream().findFirst().orElse(null);
        return Optional.ofNullable(card);
    }

    // جستجو کارت‌های فعال
    public List<Cards> findAllActiveCards() {
        TypedQuery<Cards> query = eM.createQuery(
                "SELECT c FROM Cards c WHERE c.cardsIsActive = true", Cards.class);
        return query.getResultList();
    }

    // جستجو کارت‌ها بر اساس وضعیت خاص
    public List<Cards> findByCardsStatus(Integer cardsStatus) {
        TypedQuery<Cards> query = eM.createQuery(
                "SELECT c FROM Cards c WHERE c.cardsStatus = :cardsStatus", Cards.class);
        query.setParameter("cardsStatus", cardsStatus);
        return query.getResultList();
    }

    // جستجو کارت‌ها بر اساس AccountUID
    public List<Cards> findByAccountUID(UUID accountUID) {
        TypedQuery<Cards> query = eM.createQuery(
                "SELECT c FROM Cards c WHERE c.accounts.accountUID = :accountUID", Cards.class);
        query.setParameter("accountUID", accountUID);
        return query.getResultList();
    }

    /**
     * جستجوی کارت با نام دارنده آن
     * @param AccOwnerName
     * @return
     */
    public List<Cards> findByOwnerName(String AccOwnerName) {
        return eM
                .createQuery("""
                    SELECT c FROM Cards c 
                    JOIN c.accounts a 
                    JOIN a.people p
                    WHERE p.peopleName LIKE :name
                    """, Cards.class)
                .setParameter("name", "%" + AccOwnerName + "%")
                .getResultList();
    }

}
