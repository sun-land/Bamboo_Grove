package com.sparta.team6project.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.team6project.RequestDto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter // set 함수를 일괄적으로 만들어줍니다.
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String commentUser;

    @Column(nullable = false)
    private String commentContents;

    @JsonBackReference // 순환참조 방지
    @ManyToOne
    @JoinColumn(name = "postId", nullable = false) // Post 테이블의 id(PK)와 조인
    private Post post;

    public Comment(CommentRequestDto commentRequestDto) {
        this.commentContents = commentRequestDto.getCommentContents();
    }
}
