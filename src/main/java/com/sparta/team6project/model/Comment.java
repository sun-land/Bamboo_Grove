package com.sparta.team6project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String commentUser;

    @Column(nullable = false)
    private String commentContents;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="POST_ID", nullable = false)
    private Post post;
}
