package com.sojung.blog.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{
    private HttpStatus httpStatus;

    public RestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
