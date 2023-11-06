package com.example.system_t.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Getter
@Setter
public class ApiException {
    private final String message;
    private final ZonedDateTime timestamp;

    public ApiException(String message, ZonedDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }



}
