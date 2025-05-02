package com.bnkgroup.dokhan_group.models.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "\"People\"", schema = "public")
public class People {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "PeopleUID", nullable = false)
    private UUID peopleUID;

    @Column(name = "PeopleId", nullable = false)
    private long peopleId;

    @Column(name = "PeopleName", nullable = false)
    private String peopleName;

    @Column(name = "\"PeopleStatus\"")
    private Short peopleStatus;

    @ColumnDefault("false")
    @Column(name = "\"PeopleIsActive\"")
    private Boolean peopleIsActive;

    // Constructor
    public People() {
    }

    // Getters & Setters

    public UUID getPeopleUID() {
        return peopleUID;
    }

    public void setPeopleUID(UUID peopleUID) {
        this.peopleUID = peopleUID;
    }

    public long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(long peopleId) {
        peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        peopleName = peopleName;
    }

    public Short getPeopleStatus() {
        return peopleStatus;
    }

    public void setPeopleStatus(Short peopleStatus) {
        this.peopleStatus = peopleStatus;
    }

    public Boolean getPeopleIsActive() {
        return peopleIsActive;
    }

    public void setPeopleIsActive(Boolean peopleIsActive) {
        this.peopleIsActive = peopleIsActive;
    }
}