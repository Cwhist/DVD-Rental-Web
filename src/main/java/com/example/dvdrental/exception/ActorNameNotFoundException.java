package com.example.dvdrental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActorNameNotFoundException extends RuntimeException {

    private final String name;

}
