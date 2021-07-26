package com.example.dvdrental.exception;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errorMsg;
    private String requestURL;

}
