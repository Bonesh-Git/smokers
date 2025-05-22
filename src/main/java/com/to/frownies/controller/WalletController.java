package com.to.frownies.controller;

import com.to.frownies.carrier.transaction.ReqBalanceCarrier;
import com.to.frownies.carrier.transaction.ReqTranListCarrier;
import com.to.frownies.carrier.transaction.ResBalanceCarrier;
import com.to.frownies.carrier.transaction.ResTranListCarrier;
import com.to.frownies.carrier.wallet.ReqWalletModifyCarrier;
import com.to.frownies.carrier.wallet.ResWalletModifyCarrier;
import com.to.frownies.entity.User;
import com.to.frownies.entity.Wallet;
import com.to.frownies.service.TokenService;
import com.to.frownies.service.UserService;
import com.to.frownies.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {
    private final UserService uService;
    private final WalletService wService;
    private final TokenService tokenService;

    @Autowired
    public WalletController(UserService uService, WalletService wService, TokenService tokenService) {
        this.uService = uService;
        this.wService = wService;
        this.tokenService = tokenService;
    }

    @GetMapping("balance")
    public ResBalanceCarrier balance(@RequestHeader(value = "auth-token", required = false) String tokenId) {
        return wService.getBalance(new ReqBalanceCarrier(uService.findWalletIdByToken(tokenId)));
    }

    @PostMapping("modify")
    public ResWalletModifyCarrier modify(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqWalletModifyCarrier carrier) {
        User loggedInUser = uService.findById(tokenService.checkValidation(tokenId));
        Wallet wallet = wService.findById(uService.allowed(uService.findByUsername(carrier.username()), loggedInUser).getWalletId());
        wallet.setStatus(carrier.status());
        wallet.setLastModifiedDate(Instant.now());
        wallet.setLastModifiedBy(loggedInUser.getId());
        return new ResWalletModifyCarrier(wService.update(wallet));
    }
}
