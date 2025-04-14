package com.training.springSample.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException e, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", e.getMessage());
        body.put("status", "HttpStatus not found");
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("path", request.getDescription(false).replace("uri=",""));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("Timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("Error","Input Validation Failed");
        Map<String, Object> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname=((FieldError)error).getField();
            String errormessage=((FieldError)error).getDefaultMessage();
            errors.put(fieldname,errormessage);
        });
        body.put("Message",errors);
        body.put("path", request.getDescription(false).replace("uri=",""));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
