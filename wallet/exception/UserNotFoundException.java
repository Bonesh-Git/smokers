package com.example.wallet.exception;

public class UserNotFoundException extends RuntimeException{
    private int code = 90;
    private String message = "User not found";

    public UserNotFoundException() {
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
