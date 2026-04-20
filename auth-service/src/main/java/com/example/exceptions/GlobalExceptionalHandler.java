package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionalHandler
{
     @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?>handleException(RuntimeException ex)
     {
         return ResponseEntity.badRequest().body(ex.getMessage());
     }

}
