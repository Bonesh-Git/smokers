package com.to.frownies.exception.items;

import com.to.frownies.exception.CustomExc;

public class UserNotAllowedExc extends CustomExc  {
    public UserNotAllowedExc() {
        super(40, "Access denied");
    }
}
