package com.example.dvdrental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, IdNotFoundException ex) {

        String requestURL = req.getRequestURL().toString();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("id.notfound.exception");
        errorResponse.setErrorMsg("id " + ex.getId() + " not found");

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


}
