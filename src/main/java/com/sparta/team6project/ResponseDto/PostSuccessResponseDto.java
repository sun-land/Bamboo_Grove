package com.sparta.team6project.ResponseDto;

import com.sparta.team6project.model.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

// 게시글 작성, 수정 시 반환해줄 responseDto
@Getter
public class PostSuccessResponseDto {
    private String title;
    private String contents;
    private String postUser;
    private Long postId;
    private String createdAt;

    // 생성자
    public PostSuccessResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.postUser = post.getPostUser();
        this.postId = post.getId();
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.getCreatedAt());
    }
}
