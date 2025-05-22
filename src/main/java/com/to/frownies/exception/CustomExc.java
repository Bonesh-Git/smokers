package com.to.frownies.exception;

import lombok.*;

@Getter
@Setter
public class CustomExc extends RuntimeException {
    private int code;
    private String message;
    private String detail = "";

    public CustomExc(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomExc setDetail(String detail) {
        if (detail != null && detail.length() > 0) detail = " : " + detail;
        this.detail = detail;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }
}
