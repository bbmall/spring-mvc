package com.bbmall.springmvc.exceptions;

/**
 * Created by bmalinowski on 12.05.18.
 */
public class BadParameterException extends RuntimeException {

    public BadParameterException(String message) {
        super(message);
    }
}
