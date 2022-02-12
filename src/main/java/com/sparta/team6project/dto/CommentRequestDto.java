package com.sparta.team6project.dto;

import com.sparta.team6project.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
public class CommentRequestDto {

//    private String commentUser;

    private String commentContents;

//    private Post post;

}
