package com.kidari.lecture.config.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExceptionMessage {
    private int status;
    private Date timestamp;
    private String message;
    private String description;

    public ExceptionMessage(int status, Date timestamp, String message, String description) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

}
