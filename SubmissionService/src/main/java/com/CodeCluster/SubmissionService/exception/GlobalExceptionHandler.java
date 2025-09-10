package com.CodeCluster.SubmissionService.exception;

import com.CodeCluster.SubmissionService.dto.SubmitResponseDTO;
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

    // Handles exception and sends error message to client
    @ExceptionHandler(SubmissionException.class)
    public ResponseEntity<String> handlerSubmissionException(SubmissionException ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }


}
