package com.bonesh.wallet.model.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
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
    @NonNull
    private UUID walletId;


    public User(@NonNull String fullName, @NonNull String mobileNumber, @NonNull String username, @NonNull String password) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
    }

    public @NonNull UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(@NonNull UUID walletId) {
        this.walletId = walletId;
    }

    public @NonNull String getUsername() {
        return username;
    }

    public @NonNull String getPassword() {
        return password;
    }
}
