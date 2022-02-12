package com.sparta.team6project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommentResponse {
//    private HttpStatus httpStatus;
    private String Ok;
    private String message;
}
