package com.example.wallet.carrier;

import com.example.wallet.entity.User;

import java.util.UUID;

public record TransactionWithdrawCarrier (UUID walletId, Long amount){
}
