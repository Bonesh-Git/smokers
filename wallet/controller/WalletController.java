package com.example.wallet.controller;

import com.example.wallet.carrier.*;
import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.PermissionDeniedException;
import com.example.wallet.security.LoginTokenStore;
import com.example.wallet.service.UserService;
import com.example.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final UserService service;
    private final WalletService wService;
    private final LoginTokenStore tokenStore;

    @Autowired
    public WalletController(UserService service, WalletService wService, LoginTokenStore tokenStore) {
        this.service = service;
        this.wService = wService;
        this.tokenStore = tokenStore;
    }

    @PostMapping("modify")
    public ResponseEntity<?> modify(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody ModifyWalletCarrier carrier) {
        tokenStore.findSession(token);
        User admin = service.getUserByUsername(tokenStore.getUsername(token));
        User user = service.getUserByUsername(carrier.username());

        if (!admin.isAdmin() && !admin.getId().equals(user.getId())) {
            throw new PermissionDeniedException();
        }

        Wallet wallet = wService.findById(user.getWalletId());
        wallet.setStatus(carrier.status());
        wallet.setLastModifiedDate(Instant.now());
        wallet.setLastModifiedBy(admin.getId());

        wService.update(wallet);

        return ResponseEntity.ok(user);
    }
}
