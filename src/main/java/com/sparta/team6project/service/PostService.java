package com.sparta.team6project.service;

import com.sparta.team6project.dto.PostDto;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 모든 게시물 조회
    public PostDto getAllPost(UserDetailsImpl userDetails) {
        String loginUser = null;
        // 로그인 식별하기
        if(userDetails.getUsername() != null) {
            loginUser = userDetails.getUsername();
        }
        // DB에서 시간순으로 정렬해서 불러옴
        List<Post> posts = postRepository.findAllOrderByCreatedAtDesc();
        PostDto postDto = new PostDto();
        postDto.setLoginUser(loginUser);
        postDto.setPosts(posts);

        return postDto;
    }
}
