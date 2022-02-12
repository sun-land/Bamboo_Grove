package com.sparta.team6project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommentResponse {
    //    private HttpStatus httpStatus;
    private String ok;
    private String message;

    public CommentResponse(String ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}
