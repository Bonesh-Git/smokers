package com.to.frownies.controller;

import com.to.frownies.carrier.transaction.*;
import com.to.frownies.service.TranService;
import com.to.frownies.service.UserService;
import com.to.frownies.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tran")
public class TranController {

    private final TranService tService;
    private final UserService uService;
    private final WalletService wService;

    @Autowired
    public TranController(TranService tService, UserService uService, WalletService wService) {
        this.tService = tService;
        this.uService = uService;
        this.wService = wService;
    }

    @GetMapping("list")
    public ResTranListCarrier list(@RequestHeader(value = "auth-token", required = false) String tokenId) {
        return wService.getList(new ReqTranListCarrier(uService.findWalletIdByToken(tokenId)));
    }

    @PostMapping("transfer")
    public ResBalanceCarrier transfer(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqUserTransferCarrier carrier) {
        return tService.transfer(new ReqTransferCarrier(uService.findWalletIdByToken(tokenId), uService.findByUsername(carrier.destinationUsername()).getWalletId(), carrier.amount()));
    }

    @PostMapping("withdraw")
    public ResBalanceCarrier withdraw(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqUserWithdrawCarrier carrier) {
        return tService.withdraw(new ReqWithdrawCarrier(uService.findWalletIdByToken(tokenId), carrier.amount()));
    }

    @PostMapping("deposit")
    public ResBalanceCarrier deposit(@RequestHeader(value = "auth-token", required = false) String tokenId, @RequestBody ReqUserDepositCarrier carrier) {
        return tService.deposit(new ReqDepositCarrier(uService.findWalletIdByToken(tokenId), carrier.amount()));
    }
}
