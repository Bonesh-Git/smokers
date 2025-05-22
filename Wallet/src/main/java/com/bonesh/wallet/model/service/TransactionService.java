package com.bonesh.wallet.model.service;

import com.bonesh.wallet.exception.*;
import com.bonesh.wallet.exception.IllegalArgumentException;
import com.bonesh.wallet.model.carrier.user.TransactionBalanceRequest;
import com.bonesh.wallet.model.carrier.user.TransactionDepositCarrier;
import com.bonesh.wallet.model.carrier.user.TransactionWithdrawCarrier;
import com.bonesh.wallet.model.entity.Transaction;
import com.bonesh.wallet.model.entity.TransactionType;
import com.bonesh.wallet.model.entity.Wallet;
import com.bonesh.wallet.model.entity.WalletStatus;
import com.bonesh.wallet.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    public final WalletService walletService;
    public final TransactionRepository Repository;
    @Autowired
    public TransactionService(WalletService walletService, TransactionRepository repository) {
        this.walletService = walletService;
        Repository = repository;
    }
    @Transactional
        public void deposit(TransactionDepositCarrier carrier) {
        Wallet source =walletService.findById(carrier.SourceWallet());
        Wallet destination =walletService.findById(carrier.DestinationWallet());
        if(source.equals(destination)) {
            throw new SourceAndDestinationAreSameException(2,"Source and destination are the same");
        }else if (source.getStatus().equals(WalletStatus.BLOCKED)||destination.getStatus().equals(WalletStatus.BLOCKED)||source.getStatus().equals(WalletStatus.BANNED)||destination.getStatus().equals(WalletStatus.BANNED)) {
            throw new TransactionNotAllowedException(3,"Transaction is not allowed");
        }else if (source.getBalance()<carrier.amount()){
            throw new NotSufficientFund(4,"Not sufficient fund");
        }
        source.setBalance((source.getBalance()-carrier.amount()));
        destination.setBalance(destination.getBalance()+carrier.amount());
        walletService.save(source);
        walletService.save(destination);
        Repository.save(new Transaction(TransactionType.DEBIT,carrier.amount(),carrier.SourceWallet(),carrier.DestinationWallet()));
        }
        public void withdraw(TransactionWithdrawCarrier carrier) {
        Wallet sourceWallet =walletService.findById(carrier.SourceWallet());
        if(carrier.amount()<=0L){
            throw new IllegalArgumentException(5," amount must be greater than zero");
        }
        if(sourceWallet.getStatus().equals(WalletStatus.BLOCKED)){
            throw new AllowedException(6,"Transaction is not allowed from blocked wallet");
        }
        else if (sourceWallet.getBalance()<carrier.amount()){
            throw new NotSufficientFund(4,"Not sufficient fund");
        }
        sourceWallet.setBalance(sourceWallet.getBalance()-carrier.amount());
        walletService.save(sourceWallet);
        walletService.update(sourceWallet);
        Repository.save(new Transaction(TransactionType.WITHDRAWAL,carrier.amount(),carrier.SourceWallet(),carrier.DestinationWallet()));
    }
    public Long getBalance(TransactionBalanceRequest request) {
        return walletService.findById(request.walletId()).getBalance();
    }




}




