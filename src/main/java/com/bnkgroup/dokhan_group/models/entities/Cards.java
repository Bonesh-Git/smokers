package com.bnkgroup.dokhan_group.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"Cards\"", schema = "public")
public class Cards {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "CardUID", nullable = false)
    private UUID cardUID;

    @NotNull
    @Size(max = 30)  // محدود کردن طول رشته به 30 کاراکتر
    @Pattern(
            regexp = "[0-9!\"#$%&'()*+,-./:;<=>?@\\[\\\\\\]^_`{|}~]+",
            message = "Invalid characters, only digits and special characters are allowed."
    )
    @Column(name = "\"CardNo\"", nullable = false, length = 30)
    private String cardNo;

    @NotNull
    @Column(name = "\"CardsStatus\"", nullable = false)
    private Integer cardsStatus;

    @NotNull
    @Column(name = "\"CardsIsActive\"", nullable = false)
    private Boolean cardsIsActive = false;

    @NotNull
    @Column(name = "\"DatetimeCrt\"", nullable = false)
    private OffsetDateTime datetimeCrt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "AccountUID", referencedColumnName = "AccountUID", nullable = false)
    private com.bnkgroup.dokhan_group.models.entities.Accounts accounts;

    // Constructor
    public Cards() {
    }

    // Getters & Setters

    public UUID getCardUID() {
        return cardUID;
    }

    public void setCardUID(UUID cardUID) {
        this.cardUID = cardUID;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCardsStatus() {
        return cardsStatus;
    }

    public void setCardsStatus(Integer cardsStatus) {
        this.cardsStatus = cardsStatus;
    }

    public Boolean getCardsIsActive() {
        return cardsIsActive;
    }

    public void setCardsIsActive(Boolean cardsIsActive) {
        this.cardsIsActive = cardsIsActive;
    }

    public OffsetDateTime getDatetimeCrt() {
        return datetimeCrt;
    }

    public void setDatetimeCrt(OffsetDateTime datetimeCrt) {
        this.datetimeCrt = datetimeCrt;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accountUID) {
        this.accounts = accounts;
    }
}