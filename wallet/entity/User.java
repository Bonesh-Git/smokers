package com.example.wallet.entity;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Document
public class User extends GenericEntity {
    @Field
    @NonNull
    private String fullName;
    @Field
    @NonNull
    private String mobileNumber;
    @Field
    @NonNull
    private String username;
    @Field
    @NonNull
    private String password;
    @Field
    private boolean isAdmin = false;
    @Field
    private UUID walletId;

    public User() {
    }

    public User(@NonNull String fullName, @NonNull String mobileNumber, @NonNull String username, @NonNull String password, UUID createdBy) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        if (createdBy != null) {
            setCreatedBy(createdBy);
        }
    }

    public User(@NonNull String fullName, @NonNull String mobileNumber, @NonNull String username, @NonNull String password, UUID createdBy, boolean isAdmin) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        if (createdBy != null) {
            setCreatedBy(createdBy);
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }
}

