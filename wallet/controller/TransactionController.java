package com.example.wallet.controller;

import com.example.wallet.carrier.*;
import com.example.wallet.entity.User;
import com.example.wallet.exception.PermissionDeniedException;
import com.example.wallet.security.LoginTokenStore;
import com.example.wallet.service.TransactionService;
import com.example.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService tService;
    private final UserService uService;
    private final LoginTokenStore tokenStore;

    @Autowired
    public TransactionController(TransactionService tService, UserService uService, LoginTokenStore tokenStore) {
        this.tService = tService;
        this.uService = uService;
        this.tokenStore = tokenStore;
    }

    @GetMapping("balance")
    public ResponseEntity<?> getBalance(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tService.getBalance(new TransactionBalanceRequest(uService.getUserByUsername(tokenStore.getUsername(token)).getWalletId())));
    }

    @GetMapping("list")
    public ResponseEntity<?> getTransactionList(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tService.getList(new TranListCarrier(uService.getUserByUsername(tokenStore.getUsername(token)).getWalletId())));
    }

    @PostMapping("deposit")
    public ResponseEntity<?> deposit(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody UserTransactionRequestCarrier carrier) {
        tokenStore.findSession(token);
        if (!tokenStore.getUsername(token).equals(carrier.SourceUsername())) {
            throw new PermissionDeniedException();
        }
        User source = uService.getUserByUsername(carrier.SourceUsername());
        User destination = uService.getUserByUsername(carrier.DestinationUsername());
        return ResponseEntity.ok(tService.deposit(new TransactionDepositCarrier(source.getWalletId(), destination.getWalletId(), carrier.amount())));
    }

    @PostMapping("withdraw")
    public ResponseEntity<?> withdraw(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody UserTranWithdrawCarrier carrier) {
        tokenStore.findSession(token);
        User source = uService.getUserByUsername(tokenStore.getUsername(token));
        return ResponseEntity.ok(tService.withdraw(new TransactionWithdrawCarrier(source.getWalletId(), carrier.amount())));
    }
}
