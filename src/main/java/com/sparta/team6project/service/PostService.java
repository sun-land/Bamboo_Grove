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

    public Post editPost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글 작성자 일치 확인
        if (!userDetails.getUsername().equals(foundPost.getPostUser())) {
            throw new IllegalArgumentException("타인이 작성한 게시글은 수정할 수 없습니다.");
        }

        // 게시글 업데이트
        foundPost.editPost(postRequestDto);
        return postRepository.save(foundPost);

    }

    public Long deletePost(Long postId, UserDetailsImpl userDetails) {

        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글 작성자 일치 확인
        if (!userDetails.getUsername().equals(foundPost.getPostUser())) {
            throw new IllegalArgumentException("타인이 작성한 게시글은 삭제할 수 없습니다.");
        }

        // 게시글 삭제
        postRepository.deleteById(postId);
        return postId;
    }
}
