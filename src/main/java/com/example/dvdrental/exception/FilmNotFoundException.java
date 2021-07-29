package com.example.dvdrental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmNotFoundException extends RuntimeException {

    int id;

}
