package com.sparta.team6project.controller;

import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.dto.PostResponseDto;
import com.sparta.team6project.dto.ResponseDto;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.model.User;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.sparta.team6project.dto.PostDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 작성 API
    @PostMapping("/post/write")
    public ResponseDto addPost(@RequestBody PostRequestDto postRequestDto,
                               @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            postService.addPost(postRequestDto, userDetails);
            return new ResponseDto(true, "게시글이 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            return new ResponseDto(false, message);
        }
    }

    // 게시글 수정 API
    @PutMapping("/posts/{postId}")
    public ResponseDto editPost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto postRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            postService.editPost(postId,postRequestDto,userDetails);
            return new ResponseDto(true, "게시글이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            return new ResponseDto(false, message);
        }

    }

    // 게시글 삭제 API
    @DeleteMapping("/posts/{postId}")
    public ResponseDto deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        try {
            postService.deletePost(postId, userDetails);
            return new ResponseDto(true, "게시글이 삭제되었습니다.");
        } catch(IllegalArgumentException e) {
            String message = e.getMessage();
            return new ResponseDto(false, message);
        }
    }

    // 상세 게시글 조회 API
    @GetMapping("/getposts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return postService.getPost(postId, userDetails);
    }

    @GetMapping("/allposts")
    public PostDto getAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.getAllPost(userDetails);
    }
}
