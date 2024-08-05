package com.jvsc.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
     @ExceptionHandler(FitException.class)
    public ResponseEntity<?> handleFitException(FitException ex, HttpServletRequest request) {
        var errors = ex.getErros();
        if(errors != null) {
            for(var error : errors) {
                error.setPath(request.getRequestURI());
            }
            return new ResponseEntity<>(errors, HttpStatus.valueOf(ex.getStatus()));   
        }

        return new ResponseEntity<>(new ErroResponse(ex.getMessage(), ex.getStatus(), request.getRequestURI()), HttpStatus.valueOf(ex.getStatus()));
    }
}
