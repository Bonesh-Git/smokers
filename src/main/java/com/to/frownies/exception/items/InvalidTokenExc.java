package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class InvalidTokenExc  extends CustomExc  {
    public InvalidTokenExc() {
        super(20, "Invalid auth-token");
    }
}
