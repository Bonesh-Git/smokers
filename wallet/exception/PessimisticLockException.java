package com.example.wallet.exception;

public class PessimisticLockException extends RuntimeException {
    private int code = 50;
    private String message = "Resource is locked";

    public PessimisticLockException() {
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