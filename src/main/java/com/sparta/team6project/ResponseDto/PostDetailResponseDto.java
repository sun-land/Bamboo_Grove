package com.sparta.team6project.ResponseDto;

import com.sparta.team6project.model.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 상세게시글 조회할 때 반환해줄 responseDto
@Getter
public class PostDetailResponseDto {
    private String loginUser;
    private Long postId;
    private String postUser;
    private String title;
    private String contents;
    private String createdAt;
    private List<CommentResponseDto> comments;

    // 생성자
    public PostDetailResponseDto(String loginUser, Post post, List<CommentResponseDto> comments) {
        this.loginUser = loginUser;
        this.postId = post.getId();
        this.postUser = post.getPostUser();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getCreatedAt());
        this.comments = comments;
    }


}
