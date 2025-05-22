package com.bonesh.wallet.controller;

import com.bonesh.wallet.model.carrier.user.TransactionBalanceRequest;
import com.bonesh.wallet.model.carrier.user.TransactionDepositCarrier;
import com.bonesh.wallet.model.carrier.user.UserGetWalletBalanceCarrier;
import com.bonesh.wallet.model.carrier.user.UserTransactionRequestCarrier;
import com.bonesh.wallet.model.entity.User;
import com.bonesh.wallet.model.service.TransactionService;
import com.bonesh.wallet.model.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transaction")
public class TransactionController {
    private final TransactionService tService;
    private final UserService uService;

    public TransactionController(TransactionService tService, UserService uService) {
        this.tService = tService;
        this.uService = uService;
    }

    @PostMapping
    public void deposit(@RequestBody UserTransactionRequestCarrier carrier) {
     User source=uService.getUserByUsername(carrier.sourceUsername());
     User destination=uService.getUserByUsername(carrier.destinationUsername());
     tService.deposit(new TransactionDepositCarrier(source.getWalletId(), destination.getWalletId(), carrier.amount()));

    }
    @GetMapping("/balance")
    public Long getBalance(@RequestBody UserGetWalletBalanceCarrier carrier) {
        User user=uService.getUserByUsername(carrier.username());
        return tService.getBalance(new TransactionBalanceRequest(user.getWalletId()));
    }
}
