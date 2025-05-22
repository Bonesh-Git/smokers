package com.example.wallet.service;

import com.example.wallet.carrier.*;
import com.example.wallet.entity.Transaction;
import com.example.wallet.entity.TransactionType;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.NotSufficientFund;
import com.example.wallet.exception.SourceAndDestinationAreSameException;
import com.example.wallet.exception.TransactionNotAllowedException;
import com.example.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class TransactionService {
    public final WalletService walletService;
    public final TransactionRepository repository;

    @Autowired
    public TransactionService(WalletService walletService, TransactionRepository repository) {
        this.walletService = walletService;
        this.repository = repository;
    }

    @Transactional
    public TranBalanceResCarrier deposit(TransactionDepositCarrier carrier) {
        Wallet source = walletService.findById(carrier.SourceWallet());
        Wallet destination = walletService.findById(carrier.DestinationWallet());

        if (source.getId().equals(destination.getId())) {
            throw new SourceAndDestinationAreSameException();
        } else if (source.isUnavailable() || destination.isUnavailable()) {
            throw new TransactionNotAllowedException();
        } else if (source.getBalance() < carrier.amount()) {
            throw new NotSufficientFund();
        }

        source.setBalance(source.getBalance() - carrier.amount());
        destination.setBalance(destination.getBalance() + carrier.amount());
        source.getTransactionList().add(repository.save(new Transaction(TransactionType.DEBIT, carrier.amount(), source.getId(), destination.getId(), Instant.now())).getId());
        destination.getTransactionList().add(repository.save(new Transaction(TransactionType.CREDIT, carrier.amount(), destination.getId(), source.getId(), Instant.now())).getId());
        walletService.update(source);
        walletService.update(destination);
        return new TranBalanceResCarrier(source.getBalance());
    }

    @Transactional
    public TranBalanceResCarrier withdraw(TransactionWithdrawCarrier carrier) {
        Wallet source = walletService.findById(carrier.walletId());

        if (source.isUnavailable()) {
            throw new TransactionNotAllowedException();
        } else if (source.getBalance() < carrier.amount()) {
            throw new NotSufficientFund();
        }

        source.setBalance(source.getBalance() - carrier.amount());
        source.getTransactionList().add(repository.save(new Transaction(TransactionType.DEBIT, carrier.amount(), source.getId(), null, Instant.now())).getId());
        walletService.update(source);
        return new TranBalanceResCarrier(source.getBalance());
    }

    public TranBalanceResCarrier getBalance(TransactionBalanceRequest request) {
        return new TranBalanceResCarrier(walletService.findById(request.walletId()).getBalance());
    }

    public TranListResCarrier getList(TranListCarrier carrier) {
        return new TranListResCarrier(walletService.findById(carrier.walletId()).getTransactionList());
    }
}
