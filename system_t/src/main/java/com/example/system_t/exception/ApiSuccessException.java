package com.example.system_t.exception;

public class ApiSuccessException extends RuntimeException{
    public ApiSuccessException(String message){
        super(message);
    }
}
