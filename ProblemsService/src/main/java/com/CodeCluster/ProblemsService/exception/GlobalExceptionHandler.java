package com.CodeCluster.ProblemsService.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles exception if got error in validating requests
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<String, String>();

        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    // Handles exception and sends error message to client
    @ExceptionHandler(SubmissionException.class)
    public ResponseEntity<String> handlerSubmissionException(SubmissionException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handlerSubmissionException(SQLException ex){
        log.error("something went wrong in getting problem : {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
