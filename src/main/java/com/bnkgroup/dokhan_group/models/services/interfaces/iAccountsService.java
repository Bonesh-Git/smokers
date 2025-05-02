package com.bnkgroup.dokhan_group.models.services.interfaces;

import com.bnkgroup.dokhan_group.models.entities.Accounts;
import com.bnkgroup.dokhan_group.models.repositories.AccountsRepository;

import java.util.Date;
import java.util.List;

public interface iAccountsService {

    /**
     *   افتتاح حساب جدید
     */
    public void Opening(Accounts Acnt);

    /**
     * بستن حساب موجود
     */
    public void Closing(Accounts Acnt);

    /**
     * دریافت موجودی حساب
      */
    public double ReadBalance(Date TransDate);

    /**
     * صورت گردش حساب
     */
    public List<Accounts> ReadTrans(Date TransDateFrom, Date TransDateTo);

    /**
     * مسدودسازی و رفع مسدودی حساب
     */
    public void Blocking(Accounts Acnt);

    /**
     * اتصال حساب به کارت
     */
    public void JoinToCard(Accounts Acnt);

    /**
     * انتقال وجه درون بانکی
     */
    public void InternalTransferTo(Accounts Acnt);

    /**
     * انتقال وجه بین بانکی - پایا
     */
    public void PayaTransferTo(Accounts Acnt);

    /**
     * انتقال وجه بین بانکی - ساتنا
     */
    public void SatnaTransferTo(Accounts Acnt);

    /**
     * محاسبه سود علی‌الحساب و واریز آن به حساب
     */
    public void InterestApplying(Date TransDateTo);

    /**
     * مشاهده اطلاعات و وضعیت حساب
     */
    public List<Accounts> AccountInfo();

    /**
     * جستجو در حساب
     */
    public List<Accounts> find();

}
