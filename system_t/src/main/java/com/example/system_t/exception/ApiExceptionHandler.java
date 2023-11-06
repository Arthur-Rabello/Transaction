package com.example.system_t.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiRequestException(ApiErrorException e){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", e.getMessage());
        errorResponse.put("timestamp", ZonedDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ApiSuccessException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity handleApiRequestExceptionSuccessfully(ApiErrorException e) {
        HttpStatus created = HttpStatus.OK;
        ApiException apiException = new ApiException(e.getMessage(), ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, created);

    }
}
