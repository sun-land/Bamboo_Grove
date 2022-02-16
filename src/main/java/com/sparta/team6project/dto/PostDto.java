package com.sparta.team6project.dto;

import com.sparta.team6project.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostDto {
    public String loginUser;
    public List<PostResponseDto> posts;
}
