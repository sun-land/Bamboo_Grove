package com.sparta.team6project.dto;

import com.sparta.team6project.model.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {
    private String commentContents;
    private String commentUser;
    private Long commentId;
    private String createdAt;

    public CommentResponseDto(Comment comment) {
        this.commentContents = comment.getCommentContents();
        this.commentUser = comment.getCommentUser();
        this.commentId = comment.getId();
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt());
    }
}
