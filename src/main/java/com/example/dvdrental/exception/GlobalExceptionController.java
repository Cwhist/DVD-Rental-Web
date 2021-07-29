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
        errorResponse.setRequestURL(requestURL);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ActorNameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, ActorNameNotFoundException ex) {

        String requestURL = req.getRequestURL().toString();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("actor.notfound.exception");
        errorResponse.setErrorMsg("actor named " + ex.getName() + " not found");
        errorResponse.setRequestURL(requestURL);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(FilmTitleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, FilmTitleNotFoundException ex) {

        String requestURL = req.getRequestURL().toString();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("film.notfound.exception");
        errorResponse.setErrorMsg("film titled " + ex.getTitle() + " not found");
        errorResponse.setRequestURL(requestURL);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest req, FilmNotFoundException ex) {

        String requestURL = req.getRequestURL().toString();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("film.notfound.exception");
        errorResponse.setErrorMsg("actor id " + ex.getId() + "'s film not found");
        errorResponse.setRequestURL(requestURL);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


}
