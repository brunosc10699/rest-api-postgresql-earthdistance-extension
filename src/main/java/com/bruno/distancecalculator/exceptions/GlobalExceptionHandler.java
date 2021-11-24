package com.bruno.distancecalculator.exceptions;

import com.bruno.distancecalculator.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            HttpServletRequest request, ResourceNotFoundException exception
    ) {
        int status = HttpStatus.NOT_FOUND.value();
        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(status)
                .error("Not Found")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> missingServletRequestParameter(
            HttpServletRequest request, MissingServletRequestParameterException exception
    ) {
        int status = HttpStatus.BAD_REQUEST.value();
        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(status)
                .error("Bad Request")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<StandardError> numberFormat(
            HttpServletRequest request, NumberFormatException exception
    ) {
        int status = HttpStatus.BAD_REQUEST.value();
        StandardError error = StandardError.builder()
                .timestamp(Instant.now())
                .status(status)
                .error("Bad Request")
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(error);
    }

}
