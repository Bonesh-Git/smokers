package com.example.wallet.exception;

public class TransactionNotAllowedException extends RuntimeException{
    private int code=70;
    private String message="Transaction Not Allowed";

    public TransactionNotAllowedException() {
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
