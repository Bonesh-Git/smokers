package com.bnkgroup.dokhan_group.models.services.interfaces;

import com.bnkgroup.dokhan_group.models.entities.People;
import java.util.List;
import java.util.Date;

public interface iPeopleService {

    /**
     *   ثبت شخص جدید
     */
    public void Register(People prsn);

    /**
     * روزآمدسازی مشخصات شخص موجود
     */
    public void Updating(People prsn);

    /**
     *استعلام هویت ثبت احوال ایرانیان
     */
    public boolean InquerySabtahval(Date TransDate);

    /**
     *استعلام هویت اتباع خارجی
     */
    public boolean InqueryForeign(Date TransDate);

    /**
     *استعلام اشخاص حقوقی
     */
    public boolean InqueryEnterprise(Date TransDate);

    /**
     *استعلام سوء سابقه
     */
    public boolean InqueryBadRecord(Date TransDate);

    /**
     *احراز فوت یا انحلال شخص
     */
    public void BookEnding(People prsn);

    /**
     * استعلام سابقه اعتباری شخص در بانکها
     */
    public void JoinToCard(People prsn);

    /**
     *لیست سیاه تحت تعقیب مالیاتی و ممنوعیت خروج از کشور
     */
    public void BlackListExternal(People prsn);

    /**
     * لیست سیاه ممنوعیت دریافت خدمات بانکی
     */
    public void BlackListInternal(People prsn);

    /**
     * ثبت درخواست‌های مشتریان
     */
    public void Ticketting(People prsn);

    /**
     * ثبت اطلاع‌رسانی به مشتری
     */
    public void MessagingTo(People prsn);

    /**
     * جستجو در اشخاص
     */
    public List<People> find();

    /**
     * جستجو در اشخاص براساس شناسه
     */
    public List<People> findById(Long id);

    /**
     * جستجو در اشخاص براساس نام
     */
    public List<People> findByName(String personName);

}
