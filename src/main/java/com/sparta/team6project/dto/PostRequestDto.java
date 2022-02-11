package com.sparta.team6project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PostRequestDto {

    private String title;

    private String postUser;

    private String contents;

}
