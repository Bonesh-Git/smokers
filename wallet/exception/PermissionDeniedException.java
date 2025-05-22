package com.example.wallet.exception;

public class PermissionDeniedException extends RuntimeException {
    private int code = 20;
    private String message = "User does not have the permission";

    public PermissionDeniedException() {
    }

    public PermissionDeniedException(String msg) {
        message = msg;
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
