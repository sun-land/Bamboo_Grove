package com.sparta.team6project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ResponseDto {
    private boolean ok;
    private String message;

    public ResponseDto(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}
