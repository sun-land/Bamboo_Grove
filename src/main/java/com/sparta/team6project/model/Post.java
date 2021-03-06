package com.sparta.team6project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.team6project.RequestDto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String postUser;

    @Column(nullable = false)
    private String contents;

    @JsonManagedReference // 직렬화 허용 어노테이션
    @OneToMany(mappedBy = "post", orphanRemoval = true) // orpahRemanal = true 부모 삭제시 자식도 삭제
    private List<Comment> comments;

    // 생성자
    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.postUser = postRequestDto.getPostUser();
        this.contents = postRequestDto.getContents();
    }

    // 수정 메소드
    public void editPost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }
}
