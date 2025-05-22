package com.bonesh.wallet.exception;

public class NotSufficientFund extends RuntimeException {
    private int code;
    private String message;

    public NotSufficientFund(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
