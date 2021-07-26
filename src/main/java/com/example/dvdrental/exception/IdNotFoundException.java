package com.example.dvdrental.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdNotFoundException extends RuntimeException {

    private final int id;

}
