package com.sparta.team6project.controller;

import com.sparta.team6project.dto.CommentRequestDto;
import com.sparta.team6project.exception.CommentResponse;
import com.sparta.team6project.exception.RestApiException;
import com.sparta.team6project.model.Comment;
import com.sparta.team6project.security.UserDetailsImpl;
import com.sparta.team6project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/comments/{postId}")
//    public ResponseEntity createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
    public CommentResponse createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        try {
        System.out.println(commentRequestDto.getCommentContents());
            CommentResponse commentResponse = commentService.createComment(postId, commentRequestDto, userDetails);
            return commentResponse;
//            return new ResponseEntity(comment, HttpStatus.OK);
//        } catch (IllegalArgumentException ex) {
//            RestApiException restApiException = new RestApiException();
//            restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
//            restApiException.setErrorMessage(ex.getMessage());
//            return new ResponseEntity(
//                    // HTTP body
//                    restApiException,
//                    // HTTP status code
//                    HttpStatus.BAD_REQUEST);
//        }
    }


    @PutMapping("/comments/{commentId}")
//    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
    public CommentResponse updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        try {
        CommentResponse commentResponse = commentService.updateComment(commentId, commentRequestDto, userDetails);
        return commentResponse;
//        } catch (IllegalArgumentException ex) {
//            RestApiException restApiException = new RestApiException();
//            restApiException.setOk("false");
//            restApiException.setMessage(ex.getMessage());
//            return new ResponseEntity(
//                    // HTTP body
//                    restApiException,
//                    // HTTP status code
//                    HttpStatus.BAD_REQUEST);
        }


    @DeleteMapping("/comments/{commentId}")
//    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
    public CommentResponse deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        try {
        CommentResponse commentResponse = commentService.deleteComment(commentId, commentRequestDto, userDetails);
        return commentResponse;
//        } catch (IllegalArgumentException ex) {
//            RestApiException restApiException = new RestApiException();
//            restApiException.setOk("false");
//            restApiException.setMessage(ex.getMessage());
//            return new ResponseEntity(
//                    // HTTP body
//                    restApiException,
//                    // HTTP status code
//                    HttpStatus.BAD_REQUEST);
    }

}
