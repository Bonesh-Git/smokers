package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class WalletNotFoundExc  extends CustomExc {
    public WalletNotFoundExc() {
        super(80, "Wallet not found");
    }
}
