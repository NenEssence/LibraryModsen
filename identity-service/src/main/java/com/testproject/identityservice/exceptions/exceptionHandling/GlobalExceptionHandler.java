package com.testproject.identityservice.exceptions.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDetails("Authentication error", Map.of("Error:", ex.getMessage())));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(SQLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails("Bad request", Map.of("Error:", "Username already exists")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errorMap = errors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (a, b) -> a, LinkedHashMap::new
                ));
        return ResponseEntity.badRequest().body(new ErrorDetails("Validation error", errorMap));
    }
}

