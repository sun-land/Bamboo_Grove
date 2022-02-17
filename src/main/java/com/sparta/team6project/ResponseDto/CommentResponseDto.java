package com.sparta.team6project.ResponseDto;

import com.sparta.team6project.model.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

// 댓글 작성, 수정 시 반환해줄 responseDto + 게시글 상세 조회할 때 comment리스트를 담는 responseDto
@Getter
public class CommentResponseDto {
    private String commentContents;
    private String commentUser;
    private Long commentId;
    private String createdAt;

    // 생성자
    public CommentResponseDto(Comment comment) {
        this.commentContents = comment.getCommentContents();
        this.commentUser = comment.getCommentUser();
        this.commentId = comment.getId();
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt());
    }
}
