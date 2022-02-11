package com.sparta.team6project.dto;

import com.sparta.team6project.model.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostsResponseDto {
    public String loginUser;
    public List<Post> posts;
}
