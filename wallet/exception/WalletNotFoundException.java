package com.example.wallet.exception;

public class WalletNotFoundException extends RuntimeException {
    private int code = 100;
    private String message = "Wallet not found";

    public WalletNotFoundException() {
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
