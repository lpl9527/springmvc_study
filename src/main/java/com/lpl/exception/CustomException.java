package com.lpl.exception;

/**
 * 自定义异常类
 */
public class CustomException extends Exception{

    private String message;     //异常信息

    public CustomException(){

    }
    public CustomException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
