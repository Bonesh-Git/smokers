package com.to.frownies.service;

import com.to.frownies.carrier.transaction.*;
import com.to.frownies.entity.Transaction;
import com.to.frownies.entity.Wallet;
import com.to.frownies.exception.items.LowBalanceExc;
import com.to.frownies.exception.items.SamePathExc;
import com.to.frownies.exception.items.UnavailableWalletExc;
import com.to.frownies.repository.TranRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class TranService {
    public final WalletService wService;
    public final TranRepo rep;

    @Autowired
    public TranService(WalletService wService, TranRepo rep) {
        this.wService = wService;
        this.rep = rep;
    }

    @Transactional
    public ResBalanceCarrier transfer(ReqTransferCarrier carrier) {
        // transfer cash
        Wallet source = wService.findById(carrier.sourceWalletId());
        Wallet destination = wService.findById(carrier.destinationWalletId());

        if (source.getId().equals(destination.getId())) throw new SamePathExc();
        if (!source.available() || !destination.available()) throw new UnavailableWalletExc();
        if (source.getBalance() < carrier.amount()) throw new LowBalanceExc();

        source.setBalance(source.getBalance() - carrier.amount());
        destination.setBalance(destination.getBalance() + carrier.amount());
        UUID tranId = rep.save(new Transaction(carrier.amount(), source.getId(), destination.getId(), Instant.now())).getId();
        source.getTransactionList().add(tranId);
        destination.getTransactionList().add(tranId);
        wService.update(destination);
        return new ResBalanceCarrier(wService.update(source).getBalance());
    }

    @Transactional
    public ResBalanceCarrier withdraw(ReqWithdrawCarrier carrier) {
        // get cash
        Wallet source = wService.findById(carrier.walletId());

        if (!source.available()) throw new UnavailableWalletExc();
        if (source.getBalance() < carrier.amount()) throw new LowBalanceExc();

        source.setBalance(source.getBalance() - carrier.amount());
        source.getTransactionList().add(rep.save(new Transaction(carrier.amount(), source.getId(), null, Instant.now())).getId());
        return new ResBalanceCarrier(wService.update(source).getBalance());
    }

    @Transactional
    public ResBalanceCarrier deposit(ReqDepositCarrier carrier) {
        // put cash
        Wallet source = wService.findById(carrier.walletId());
        if (!source.available()) throw new UnavailableWalletExc();
        source.setBalance(source.getBalance() + carrier.amount());
        source.getTransactionList().add(rep.save(new Transaction(carrier.amount(), null, source.getId(), Instant.now())).getId());
        return new ResBalanceCarrier(wService.update(source).getBalance());
    }
}
