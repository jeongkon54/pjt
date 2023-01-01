package com.kidari.lecture.config.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int status;

    public DataNotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }
}
