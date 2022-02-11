package com.sparta.team6project.controller;

import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post/write")
    public Post addPost(
            @RequestBody PostRequestDto postRequestDto
    ) {
        // 로그인 연결 전 임시 객체
        UserDetailsImpl userDetails = new UserDetailsImpl();

        return postService.addPost(postRequestDto, userDetails);
    }
}
