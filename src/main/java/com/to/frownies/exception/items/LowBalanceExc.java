package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class LowBalanceExc  extends CustomExc  {
    public LowBalanceExc() {
        super(30, "Not enough balance");
    }
}
