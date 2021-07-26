package com.example.dvdrental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmTitleNotFoundException extends RuntimeException {

    private final String title;

}
