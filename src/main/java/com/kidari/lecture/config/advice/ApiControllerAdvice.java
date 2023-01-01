package com.kidari.lecture.config.advice;

import com.kidari.lecture.config.exception.ApiParameterException;
import com.kidari.lecture.config.exception.DataNotFoundException;
import com.kidari.lecture.config.exception.ExceptionMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    private final ModelMapper modelMapper;

    public ApiControllerAdvice(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest webRequest)
    {
        return new ResponseEntity<>("Authentication Failed", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ApiParameterException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleApiParameterException(Exception exception, WebRequest webRequest) {
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleAllExceptions(Exception exception, WebRequest webRequest)
    {
        ExceptionMessage errorMessage = modelMapper.map(exception, ExceptionMessage.class);
        errorMessage.setStatus(500);
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleAllExceptions(DataNotFoundException exception, WebRequest webRequest)
    {
        ExceptionMessage errorMessage = modelMapper.map(exception, ExceptionMessage.class);
        errorMessage.setStatus(exception.getStatus());
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}