package com.sparta.team6project.service;


import com.sparta.team6project.dto.PostRequestDto;
import com.sparta.team6project.dto.PostDetailResponseDto;
import com.sparta.team6project.dto.PostDto;
import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.CommentRepository;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 작성 메소드
    public void addPost(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {



        // 게시글에 작성자 추가
        String postUser = userDetails.getUsername();
        postRequestDto.setPostUser(postUser);


        // 게시글 확인
        if (postRequestDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        } else if (postRequestDto.getContents().trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }

        // 게시글 저장
        Post post = new Post(postRequestDto);
        postRepository.save(post);
    }

    // 게시글 수정 메소드
    public void editPost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {
        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글 작성자 일치 확인
        if (!userDetails.getUsername().equals(foundPost.getPostUser())) {
            throw new IllegalArgumentException("타인이 작성한 게시글은 수정할 수 없습니다.");
        }

        // 게시글 수정 내용 확인
        if (postRequestDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        } else if (postRequestDto.getContents().trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }

        // 게시글 업데이트
        foundPost.editPost(postRequestDto);
        postRepository.save(foundPost);

    }

    // 게시글 삭제 메소드
    public void deletePost(Long postId, UserDetailsImpl userDetails) {

        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글 작성자 일치 확인
        if (!userDetails.getUsername().equals(foundPost.getPostUser())) {
            throw new IllegalArgumentException("타인이 작성한 게시글은 삭제할 수 없습니다.");
        }

        // 댓글 먼저 삭제
        List<Comment> foundComments = commentRepository.findAllByPost(foundPost);
        commentRepository.deleteAll(foundComments);

        // 게시글 삭제
        postRepository.deleteById(postId);
    }

    // 상세 게시글 조회 메소드
    public PostDetailResponseDto getPost(Long postId, UserDetailsImpl userDetails) {
        String loginUser = null;
        // 로그인 식별하기
        if(userDetails != null) {
            loginUser = userDetails.getUsername();
        }
        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // responseDTO에 담아 보내기
        return new PostDetailResponseDto(loginUser, foundPost);
    }

    // 모든 게시물 조회
    public PostDto getAllPost(UserDetailsImpl userDetails) {
        String loginUser = null;
        // 로그인 식별하기
        if(userDetails != null) {
            loginUser = userDetails.getUsername();
        }
        // DB에서 시간순으로 정렬해서 불러옴
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        PostDto postDto = new PostDto();
        postDto.setLoginUser(loginUser);
        postDto.setPosts(posts);

        return postDto;

    }
}
