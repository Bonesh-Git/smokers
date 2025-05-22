package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class DupUserExc  extends CustomExc  {
    public DupUserExc() {
        super(10, "Username already exists");
    }
}
