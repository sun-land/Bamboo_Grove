package com.sparta.team6project.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllPostDto {
    public String loginUser;
    public List<PostResponseDto> posts;
}
