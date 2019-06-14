package com.finalproject.walletforex.exception;

public class UserAlreadyException extends Exception {
    private int code;
    private String message;

    public UserAlreadyException(int code, String message){
        super();
        this.code = code;
        this.message = message;
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

    public void setMessage(String mesage) {
        this.message = mesage;
    }
}
