package com.sparta.team6project.ResponseDto;

import com.sparta.team6project.model.Comment;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {
    // 댓글 내용
    private String commentContents;
    // 댓글 작성자
    private String commentUser;
    // 댓글 아이디
    private Long commentId;
    // 댓글 생성 시간
    private String createdAt;

    public CommentResponseDto(Comment comment) {
        this.commentContents = comment.getCommentContents();
        this.commentUser = comment.getCommentUser();
        this.commentId = comment.getId();
        // 댓글 생성 시간을 년-월-일 시:분:초 포맷으로 응답해주기 위한 설정
        this.createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(comment.getCreatedAt());
    }
}
