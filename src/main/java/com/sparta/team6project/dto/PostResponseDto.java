package com.sparta.team6project.dto;

import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private String loginUser;
    private Long postId;
    private String postUser;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private List<Comment> comments;

    public PostResponseDto(String loginUser, Post post) {
        this.loginUser = loginUser;
        this.postId = post.getId();
        this.postUser = post.getPostUser();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.comments = post.getComments();
    }


}
