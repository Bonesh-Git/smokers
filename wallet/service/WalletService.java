package com.example.wallet.service;

import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.OptimisticLockException;
import com.example.wallet.exception.PessimisticLockException;
import com.example.wallet.exception.WalletNotFoundException;
import com.example.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service

public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public UUID save(Wallet wallet)  {
        wallet.setCreatedDate(Instant.now());
        return walletRepository.save(wallet).getId();
    }

    @Transactional
    public void update(Wallet wallet) {
        Wallet existing = findById(wallet.getId());

        if (existing == null) {
            throw new PessimisticLockException();
        }
        if (!existing.getVersion().equals(wallet.getVersion())) {
            throw new OptimisticLockException();
        }

        walletRepository.save(wallet);
    }

    public Wallet findById(UUID id) {
        return walletRepository.findById(id).orElseThrow(WalletNotFoundException::new);
    }

}
