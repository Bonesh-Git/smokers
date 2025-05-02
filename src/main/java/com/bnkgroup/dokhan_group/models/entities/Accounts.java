package com.bnkgroup.dokhan_group.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"Accounts\"", schema = "public")
public class Accounts {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "AccountUID", nullable = false)
    private UUID accountUID;

    @Size(max = 25)
    @NotNull
    @Column(name = "\"AccountNo\"", nullable = false, length = 25)
    private String accountNo;

    @NotNull
    @Column(name = "\"AccountType\"", nullable = false)
    private Integer accountType;

    @Column(name = "\"OpenDate\"")
    private OffsetDateTime openDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PeopleUID", referencedColumnName = "PeopleUID", nullable = false)
    private com.bnkgroup.dokhan_group.models.entities.People people;

    //  Constructor
    public Accounts() {

    }

    // Getters & Setters

    public UUID getAccountUID() {
        return accountUID;
    }

    public void setAccountUID(UUID accountUID) {
        this.accountUID = accountUID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public OffsetDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(OffsetDateTime openDate) {
        this.openDate = openDate;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
