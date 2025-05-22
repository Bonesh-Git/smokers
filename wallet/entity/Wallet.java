package com.example.wallet.entity;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Wallet extends GenericEntity {
    @Field
    @NonNull
    private long balance;
    @Field
    private List<UUID> transactionList;
    @Field
    private WalletStatus status = WalletStatus.NORMAL;

    public Wallet() {
    }

    public Wallet(long balance) {
        this.balance = balance;
    }

    public Wallet(@NonNull long balance, List<UUID> transactionList, WalletStatus status) {
        this.balance = balance;
        this.transactionList = transactionList;
        this.status = status;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<UUID> getTransactionList() {
        if (transactionList == null) {
            transactionList= new ArrayList<>();
        }
        return transactionList;
    }

    public void setTransactionList(List<UUID> transactionList) {
        this.transactionList = transactionList;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
    }

    public boolean isUnavailable() {
        return status.equals(WalletStatus.BANNED) || status.equals(WalletStatus.BLOCKED) || status.equals(WalletStatus.CLOSED);
    }
}
