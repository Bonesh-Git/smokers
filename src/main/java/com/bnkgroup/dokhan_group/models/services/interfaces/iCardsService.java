package com.bnkgroup.dokhan_group.models.services.interfaces;

import com.bnkgroup.dokhan_group.models.entities.Cards;
import com.bnkgroup.dokhan_group.models.repositories.CardsRepository;

import java.util.Date;
import java.util.List;

public interface iCardsService {

    /**
     *   صدور کارت جدید
     */
    public void Issuing(Cards crd);

    /**
     * باطل کردن کارت موجود
     */
    public void Terminating(Cards crd);

    /**
     * فعال‌سازی و غیرفعال‌سازی کارت
     */
    public void ActivateStatus(Cards crd);

    /**
     * مسدودسازی و رفع مسدودی کارت
     */
    public void Blocking(Cards crd);

    /**
     * تغییر رمز اول کارت
     */
    public void ChangeKey1(Cards crd);

    /**
     * تغییر رمز دوم کارت
     */
    public void ChangeKey2(Cards crd);

    /**
     * فعال و غیرفعال‌سازی رمز دوم پویا
     */
    public void PooyaKey2Status(Cards crd);

    /**
     * دریافت موجودی حساب متصل به کارت
     */
    public double ReadBalance(Date TransDate);

    /**
     * صورت گردش حساب متصل به کارت
     */
    public List<CardsRepository> ReadTrans(Date TransDateFrom, Date TransDateTo);

    /**
     * اتصال کارت به حساب
     */
    public void JoinToAccount(Cards crd);

    /**
     * انتقال وجه کارت به کارت
     */
    public void ShetabTransferTo(Cards crd);

    /**
     * مشاهده اطلاعات و وضعیت کارت
     */
    public List<Cards> CardInfo(Cards crd);

    /**
     * جستجو در کارتهای یک شخص
     */
    public List<Cards> find();

}
