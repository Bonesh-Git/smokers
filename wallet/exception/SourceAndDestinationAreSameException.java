package com.example.wallet.exception;

public class SourceAndDestinationAreSameException extends RuntimeException {
    private int code=60;
    private String message="Source and destination are same";

    public SourceAndDestinationAreSameException() {
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
