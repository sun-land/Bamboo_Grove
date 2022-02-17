package com.sparta.team6project.RequestDto;

import lombok.Getter;
import lombok.Setter;

// 게시글 생성, 수정할 때 내용을 담아오는 requestDto
@Getter
@Setter
public class PostRequestDto {

    private String title;

    private String postUser;

    private String contents;

}
