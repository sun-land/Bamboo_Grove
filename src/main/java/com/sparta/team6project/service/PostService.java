package com.sparta.team6project.service;

import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Post addPost(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        String postUser = userDetails.getUsername();
        postRequestDto.setPostUser(postUser);
        Post post = new Post(postRequestDto);
        return postRepository.save(post);
    }
}
