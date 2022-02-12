package com.sparta.team6project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    //    private HttpStatus httpStatus;
    private String errorMessage;
    private String Ok;
}