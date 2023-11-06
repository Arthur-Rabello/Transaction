package com.example.system_t.exception;

public class ApiErrorException extends RuntimeException{

    public ApiErrorException(String message) {
        super(message);
    }

}
