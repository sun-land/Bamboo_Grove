package com.sparta.team6project.model;

import com.sparta.team6project.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String commentUser;

    @Column(nullable = false)
    private String commentContents;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false) // Post 테이블의 id(PK)와 조인
    private Post post;


    // 음식점 등록 시 이용합니다.
    public Comment(CommentRequestDto commentRequestDto) {
//        this.commentUser = commentRequestDto.getCommentUser();
        this.commentContents = commentRequestDto.getCommentContents();
//        this.post = commentRequestDto.getPost();
    }


}
