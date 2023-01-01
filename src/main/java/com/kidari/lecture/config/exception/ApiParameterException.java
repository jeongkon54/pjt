package com.kidari.lecture.config.exception;

public class ApiParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApiParameterException(String msg) {
        super(msg);
    }
}
