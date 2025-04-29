package com.bonesh.paya.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue
    private long id;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String nationalCode;

    public Customer(String firstName, String lastName, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
    }
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Deposit> deposits;

}

