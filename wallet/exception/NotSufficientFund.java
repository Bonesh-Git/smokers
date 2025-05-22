package com.example.wallet.exception;

public class NotSufficientFund extends RuntimeException{
    private int code=30;
    private String message="Not Sufficient Fund";

    public NotSufficientFund() {
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
