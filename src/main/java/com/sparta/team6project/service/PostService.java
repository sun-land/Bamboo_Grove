package com.sparta.team6project.service;


import com.sparta.team6project.RequestDto.PostRequestDto;
import com.sparta.team6project.ResponseDto.*;
import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.CommentRepository;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 작성 메소드
    @Transactional
    public PostSuccessResponseDto addPost(PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        // 게시글에 작성자 추가
        String postUser = userDetails.getUsername();
        postRequestDto.setPostUser(postUser);

        // 게시글 내용이 비어있는지 체크
        isValidPost(postRequestDto);

        // 게시글 저장
        Post post = new Post(postRequestDto);
        Post savePost = postRepository.save(post);

        return new PostSuccessResponseDto(savePost);
    }

    // 게시글 수정 메소드
    @Transactional
    public PostSuccessResponseDto editPost(Long postId, PostRequestDto postRequestDto, UserDetailsImpl userDetails) {

        // 게시글 유무, 게시글 작성자 일치 확인
        Post foundPost = isValidPostAndPostUser(postId, userDetails);

        // 게시글 수정 내용이 비어있는지 체크
        isValidPost(postRequestDto);

        // 게시글 업데이트
        foundPost.editPost(postRequestDto);
        Post editPost = postRepository.save(foundPost);

        return new PostSuccessResponseDto(editPost);

    }

    // 게시글 삭제 메소드
    @Transactional
    public HashMap<String, Long> deletePost(Long postId, UserDetailsImpl userDetails) {

        // 게시글 유무, 게시글 작성자 일치 확인
        Post foundPost = isValidPostAndPostUser(postId, userDetails);

        // Response 만들기
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", foundPost.getId());

        // 게시글 삭제
        postRepository.deleteById(postId);

        return responseId;
    }

    // 상세 게시글 조회 메소드
    public PostDetailResponseDto getPost(Long postId, UserDetailsImpl userDetails) {

        // loginUser 기본값 null
        String loginUser = null;

        // 로그인 식별하기
        if(userDetails != null) {
            loginUser = userDetails.getUsername();
        }

        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글의 댓글들을 commentResponseDto에 담아 반환해줄 comments 만들기
        List<CommentResponseDto> comments = createCommentResponseDto(foundPost);

        // responseDTO에 담아 보내기
        return new PostDetailResponseDto(loginUser, foundPost, comments);
    }

    // 모든 게시물 조회
    public AllPostDto getAllPost(UserDetailsImpl userDetails) {
        String loginUser = null;
        // 로그인 식별하기
        if(userDetails != null) {
            loginUser = userDetails.getUsername();
        }
        // DB에서 시간순으로 정렬해서 불러옴
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> responseDtos = new ArrayList<>();
        // 생성시간 포멧을 바꾸기위해 꺼내서 createdAt의 포멧을 바꿔줘서 responseDtos 에 하나씩 넣어줌
        for (int i = 0; i < posts.size(); i++){
            PostResponseDto dto = new PostResponseDto();
            dto.setPostId(posts.get(i).getId());
            dto.setPostUser(posts.get(i).getPostUser());
            dto.setCommentCount(posts.get(i).getComments().size());
            dto.setTitle(posts.get(i).getTitle());
            dto.setCreatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(posts.get(i).getCreatedAt()));
            responseDtos.add(dto);
        }
        // postDto에 담아서 보내기
        AllPostDto postDto = new AllPostDto();
        postDto.setLoginUser(loginUser);
        postDto.setPosts(responseDtos);

        return postDto;


    }

    // 게시글 제목과 내용 빈칸인지 확인하는 메소드
    private void isValidPost(PostRequestDto postRequestDto) {
        if (postRequestDto.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        } else if (postRequestDto.getContents().trim().isEmpty()) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
    }

    // 게시글 유무와 게시글 작성자 일치여부 확인 메소드
    private Post isValidPostAndPostUser(Long postId, UserDetailsImpl userDetails) {

        // 게시글 유무 확인
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 게시글 작성자 일치 확인
        if (!userDetails.getUsername().equals(foundPost.getPostUser())) {
            throw new IllegalArgumentException("타인이 작성한 게시글은 수정 및 삭제가 불가합니다.");
        }

        return foundPost;
    }

    // 게시글의 댓글들 CommentResponseDto에 담기
    private List<CommentResponseDto> createCommentResponseDto(Post foundPost) {
        List<CommentResponseDto> comments = new ArrayList<>();
        for (Comment comment : foundPost.getComments()) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            comments.add(commentResponseDto);
        }
        return comments;
    }
}
