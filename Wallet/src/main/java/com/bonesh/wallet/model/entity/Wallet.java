package com.bonesh.wallet.model.entity;

import lombok.*;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Wallet extends GenericEntity{
    @Field
    @NonNull
    private Long balance;
    @Field
    List<UUID> transactionList;
    @Field
    @NonNull
    private WalletStatus status=WalletStatus.NORMAL;
    public Wallet(Long balance) {
        this.balance = balance;
    }

    public @NonNull WalletStatus getStatus() {
        return status;
    }

    public @NonNull Long getBalance() {
        return balance;
    }

    public void setBalance(@NonNull Long balance) {
        this.balance = balance;
    }
}
