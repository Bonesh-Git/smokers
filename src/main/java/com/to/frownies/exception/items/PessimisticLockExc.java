package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class PessimisticLockExc extends CustomExc {
    public PessimisticLockExc() {
        super(90, "Source locked or not found for replacement");
    }
}
