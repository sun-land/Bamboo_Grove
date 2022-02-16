package com.sparta.team6project.dto;

import com.sparta.team6project.model.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostSuccessResponseDto {
    private String title;
    private String contents;
    private String postUser;
    private Long postId;
    private String createdAt;

    public PostSuccessResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.postUser = post.getPostUser();
        this.postId = post.getId();
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getCreatedAt());
    }
}
