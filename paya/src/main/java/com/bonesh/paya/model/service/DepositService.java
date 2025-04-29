package com.bonesh.paya.model.service;

import com.bonesh.paya.model.MessageSender;
import com.bonesh.paya.model.carrier.DepositDefineCarrier;
import com.bonesh.paya.model.entity.Deposit;
import com.bonesh.paya.model.repository.DepositRepository;

import java.util.Optional;

public class DepositService {
    private DepositRepository depositRepository = new DepositRepository();
    private MessageSender messageSender = new MessageSender();

    public void define(DepositDefineCarrier carrier) {
        Deposit deposit = new Deposit(carrier.shabaNumber(), carrier.balance());
        depositRepository.save(deposit);
    }

    public void withdrawAndSend(long shabaNumberSender, long shabaNumberReceiver, long balance) {
        Optional<Deposit> depositOptionalSender = depositRepository.findByShabaNumber(shabaNumberSender);
        Optional<Deposit> depositOptionalReceiver = depositRepository.findByShabaNumber(shabaNumberReceiver);
        if (depositOptionalSender.isPresent() && depositOptionalReceiver.isPresent()) {
            Deposit deposit = depositOptionalSender.get();
            if (deposit.getBalance() >= balance) {
                deposit.setBalance(deposit.getBalance() - balance);
                depositRepository.update();

                messageSender.sendWithdrawalMessage(shabaNumberReceiver, balance);
                messageSender.closeConnection();
            }
        }

    }

}