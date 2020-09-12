package com.projectmanagement.api.exceptions;
import com.projectmanagement.api.responces.ExceptionResponce;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

//    @ExceptionHandler(value=Exception.class)
//    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                new Date(),
//                request.getDescription(false),
//                HttpStatus.NOT_ACCEPTABLE.toString(),
//                ex.getMessage());
//
//        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
//    }


    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<Object> HandleOtherExceptions(Exception ex, WebRequest request)
    {
        ExceptionResponce exceptionResponce = new ExceptionResponce(
                new Date(),
                ex.getMessage(),
                request.getDescription(false),
                HttpStatus.NOT_ACCEPTABLE.toString());

        return new ResponseEntity<>(exceptionResponce, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
