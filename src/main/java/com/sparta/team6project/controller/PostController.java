package com.sparta.team6project.controller;

import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.dto.PostResponseDto;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.model.User;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 작성 API
    @PostMapping("/post/write")
    public Post addPost(@RequestBody PostRequestDto postRequestDto) {
        // 로그인 연결 전 임시 객체
        UserDetailsImpl userDetails = new UserDetailsImpl();

        return postService.addPost(postRequestDto, userDetails);
    }

    // 게시글 수정 API
    @PutMapping("/posts/{postId}")
    public Post editPost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto
    ) {
        // 로그인 연결 전 임시 객체
        UserDetailsImpl userDetails = new UserDetailsImpl();

        return postService.editPost(postId,postRequestDto,userDetails);
    }

    // 게시글 삭제 API
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(
            @PathVariable Long postId
    ) {
        // 로그인 연결 전 임시 객체
        UserDetailsImpl userDetails = new UserDetailsImpl();

        return postService.deletePost(postId,userDetails);
    }

    // 상세 게시글 조회 API
    @GetMapping("/getposts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {

        // 로그인 연결 전 임시 객체
        UserDetailsImpl userDetails = new UserDetailsImpl();

        return postService.getPost(postId, userDetails);
    }
}
