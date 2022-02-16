package com.sparta.team6project.controller;

import com.sparta.team6project.dto.CommentRequestDto;
import com.sparta.team6project.dto.SuccessResponseDto;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/comments/{postId}")
//    public ResponseEntity createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
    public HashMap<String, Long> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(commentRequestDto.getCommentContents());

        return commentService.createComment(postId, commentRequestDto, userDetails);

    }


    @PutMapping("/comments/{commentId}")
//    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {

    public HashMap<String, Long> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails);

        }


    @DeleteMapping("/comments/{commentId}")
//    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
    public HashMap<String, Long> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails);

    }
}
