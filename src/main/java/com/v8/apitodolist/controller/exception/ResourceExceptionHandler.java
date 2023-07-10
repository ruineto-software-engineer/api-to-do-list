package com.v8.apitodolist.controller.exception;

import com.v8.apitodolist.service.exception.EntityBadRequestException;
import com.v8.apitodolist.service.exception.EntityConflictException;
import com.v8.apitodolist.service.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(
            EntityNotFoundException entityNotFoundException, HttpServletRequest request
    ) {
        StandardError standardError = new StandardError();

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setError("Resource not found");
        standardError.setMessage(entityNotFoundException.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(EntityConflictException.class)
    public ResponseEntity<StandardError> entityConflictException(
            EntityConflictException entityConflictException, HttpServletRequest request
    ) {
        StandardError standardError = new StandardError();

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.CONFLICT.value());
        standardError.setError("Resource conflicting with an existing resource");
        standardError.setMessage(entityConflictException.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(standardError);
    }

    @ExceptionHandler(EntityBadRequestException.class)
    public ResponseEntity<StandardError> entityBadRequestException(
            EntityBadRequestException entityBadRequestException, HttpServletRequest request
    ) {
        StandardError standardError = new StandardError();

        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setError("Resource with invalid request");
        standardError.setMessage(entityBadRequestException.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
