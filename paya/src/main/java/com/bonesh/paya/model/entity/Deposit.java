package com.bonesh.paya.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deposits")
@Getter
@Setter
@NoArgsConstructor
public class Deposit {
    @Id
    @GeneratedValue
    private long id;
    @Basic
    private long shabaNumber;
    @Basic
    private long balance;

    public Deposit(long shabaNumber, long balance) {
        this.shabaNumber = shabaNumber;
        this.balance = balance;
    }
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CID")
    private Customer customer;
}

