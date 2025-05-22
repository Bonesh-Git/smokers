package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class UnavailableWalletExc  extends CustomExc  {
    public UnavailableWalletExc() {
        super(60, "Wallet is unavailable");
    }
}
