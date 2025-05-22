package com.example.wallet.exception;

import lombok.Setter;

public class UserAlreadyExist extends RuntimeException {
    private int code=80;
    @Setter
    private String message="Username already exist";

    public UserAlreadyExist() {
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
