package com.example.wallet.exception;

public class OptimisticLockException extends RuntimeException {
    private int code = 40;
    private String message = "Versions not match";

    public OptimisticLockException() {
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