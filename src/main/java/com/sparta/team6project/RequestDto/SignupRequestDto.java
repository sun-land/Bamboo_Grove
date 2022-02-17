package com.sparta.team6project.RequestDto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String confirmPassword;
}
