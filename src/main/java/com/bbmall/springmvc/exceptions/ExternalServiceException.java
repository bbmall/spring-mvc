package com.bbmall.springmvc.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class ExternalServiceException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String message;

    public ExternalServiceException(HttpStatus statusCode, String message) {

        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
