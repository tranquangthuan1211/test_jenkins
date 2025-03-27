package com.example.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USER_NOT_EXITS(1005,"hello anh em");
    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;

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
}
