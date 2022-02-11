package com.sparta.team6project.controller;

import com.sparta.team6project.dto.PostDto;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public PostDto getAllPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.getAllPost(userDetails);
    }
}
