package com.example.wallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.UUID;

@Document
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private UUID id;
    @Field
    private Instant createdDate;
    @Version
    private Long version;
    @Field
    @NonNull
    private TransactionType type;
    @Field
    private long amount; //mande
    @Field
    @NonNull
    private UUID sourceWallet;
    @Field
    @NonNull
    private UUID destinationWallet;

    public Transaction() {
    }

    public Transaction(@NonNull TransactionType type, long amount, @NonNull UUID sourceWallet, @NonNull UUID destinationWallet, Instant createdDate) {
        this.type = type;
        this.amount = amount;
        this.sourceWallet = sourceWallet;
        this.destinationWallet = destinationWallet;
        this.createdDate=createdDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public UUID getSourceWallet() {
        return sourceWallet;
    }

    public void setSourceWallet(UUID sourceWallet) {
        this.sourceWallet = sourceWallet;
    }

    public UUID getDestinationWallet() {
        return destinationWallet;
    }

    public void setDestinationWallet(UUID destinationWallet) {
        this.destinationWallet = destinationWallet;
    }
}
