package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class UserNotFoundExc extends CustomExc {
    public UserNotFoundExc() {
        super(70, "User not found");
    }
}
