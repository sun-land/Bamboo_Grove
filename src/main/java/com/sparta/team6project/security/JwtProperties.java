package com.sparta.team6project.security;

public interface JwtProperties {
    // 시크릿 키
    String secretKey = "oursecretkey";
    // 토큰 유효 시간
    Long tokenValidTime = 60000*60L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
