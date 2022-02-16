package com.sparta.team6project.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PostResponseDto {
    private Long postId;
    private String postUser;
    private String title;
    private int commentCount;
    private String createdAt;
}
