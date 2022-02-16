package com.sparta.team6project.dto;

import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PostDetailResponseDto {
    private String loginUser;
    private Long postId;
    private String postUser;
    private String title;
    private String contents;
    private String createdAt;
    private List<CommentResponseDto> comments;

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
