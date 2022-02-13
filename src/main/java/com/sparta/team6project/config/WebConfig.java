package com.sparta.team6project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                //.allowedOrigins("http://localhost:8080", "http://mintest1.s3-website.ap-northeast-2.amazonaws.com/")
                .allowedMethods("*")
                .maxAge(3000);
    }
}
