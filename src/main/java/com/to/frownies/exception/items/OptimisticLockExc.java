package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class OptimisticLockExc extends CustomExc {
    public OptimisticLockExc() {
        super(100, "Update your data version");
    }
}
