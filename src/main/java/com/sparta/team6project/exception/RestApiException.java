package com.sparta.team6project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestApiException {
    // 동작 성공은 true, 실패는 false
    private boolean ok;
    // 에러원인메시지
    private String errorMessage;
}