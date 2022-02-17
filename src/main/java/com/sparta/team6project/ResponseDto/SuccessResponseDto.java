package com.sparta.team6project.ResponseDto;

import lombok.Getter;

@Getter
public class SuccessResponseDto {
    private boolean ok;
    private String message;

    public SuccessResponseDto(String message) {
        this.ok = true;
        this.message = message;
    }
}
