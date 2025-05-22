package com.example.wallet.carrier;

import com.example.wallet.entity.WalletStatus;

import java.util.UUID;

public record ModifyWalletCarrier (String username, WalletStatus status){
}
