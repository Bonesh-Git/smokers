package com.to.frownies.service;

import com.to.frownies.carrier.transaction.ReqBalanceCarrier;
import com.to.frownies.carrier.transaction.ReqTranListCarrier;
import com.to.frownies.carrier.transaction.ResBalanceCarrier;
import com.to.frownies.carrier.transaction.ResTranListCarrier;
import com.to.frownies.entity.Wallet;
import com.to.frownies.exception.items.OptimisticLockExc;
import com.to.frownies.exception.items.PessimisticLockExc;
import com.to.frownies.exception.items.WalletNotFoundExc;
import com.to.frownies.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class WalletService {
    private final WalletRepo rep;

    @Autowired
    public WalletService(WalletRepo rep) {
        this.rep = rep;
    }

    @Transactional
    public UUID save(Wallet wallet) {
        wallet.setCreatedDate(Instant.now());
        return rep.save(wallet).getId();
    }

    @Transactional
    public Wallet update(Wallet wallet) {
        Wallet existing = findById(wallet.getId());

        if (existing == null) {
            throw new PessimisticLockExc();
        }
        if (!existing.getVersion().equals(wallet.getVersion())) {
            throw new OptimisticLockExc();
        }

        return rep.save(wallet);
    }

    public Wallet findById(UUID id) {
        return rep.findById(id).orElseThrow(WalletNotFoundExc::new);
    }

    public ResBalanceCarrier getBalance(ReqBalanceCarrier carrier) {
        return new ResBalanceCarrier(findById(carrier.walletId()).getBalance());
    }

    public ResTranListCarrier getList(ReqTranListCarrier carrier) {
        return new ResTranListCarrier(findById(carrier.walletId()).getTransactionList());
    }

}
