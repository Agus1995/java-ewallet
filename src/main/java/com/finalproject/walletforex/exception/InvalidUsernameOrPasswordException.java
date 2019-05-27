package com.finalproject.walletforex.exception;

public class InvalidUsernameOrPasswordException extends Exception{
    private int code;
    private String mesage;

    public InvalidUsernameOrPasswordException(int code, String message){
        super();
        this.code = code;
        this.mesage = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }
}
