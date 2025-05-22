package com.example.wallet.exception;

public class InvalidToken extends RuntimeException{
    private int code=10;
    private String message="Invalid Or Missing Token";

    public InvalidToken() {
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
