package com.bonesh.wallet.model.service;


import com.bonesh.wallet.model.carrier.user.TransactionBalanceRequest;
import com.bonesh.wallet.model.entity.Wallet;
import com.bonesh.wallet.model.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }
    public UUID save(Wallet wallet) {
        return walletRepository.save(wallet).getId();
    }
    public Wallet findById(UUID id) {
        return walletRepository.findById(id).orElseThrow();
    }
    public void update(Wallet wallet) {
        walletRepository.save(wallet);
    }
    }


