package com.CodeCluster.UserService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles exception if got error in validating requests
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<String, String>();

        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    // Handles duplication email exception
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<String> handleEmailExistException(EmailExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Handler duplicate username exception
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<String> handleEmailExistException(UsernameExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
