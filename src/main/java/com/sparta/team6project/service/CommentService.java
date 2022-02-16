package com.sparta.team6project.service;


import com.sparta.team6project.dto.CommentRequestDto;
import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.CommentRepository;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


<<<<<<< HEAD
    public HashMap<String, Long> createComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
=======

    public void createComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
>>>>>>> bc62a1376e5a4a0289fc6f692f265cbaadd6c8b3

        Comment comment = new Comment(commentRequestDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("게시글이 존재하지 않습니다."));
        comment.setCommentUser(userDetails.getUsername());
        comment.setPost(post);

        String commentUser = comment.getCommentUser();

        if(commentUser==null || commentUser==""){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        } else{
<<<<<<< HEAD
            Comment saveComment = commentRepository.save(comment);
            HashMap<String, Long> responseId = new HashMap<>();
            responseId.put("commentId", saveComment.getId());
            return responseId;
=======
            commentRepository.save(comment);
>>>>>>> bc62a1376e5a4a0289fc6f692f265cbaadd6c8b3

        }
    }



<<<<<<< HEAD
    public HashMap<String, Long> updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
=======
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
>>>>>>> bc62a1376e5a4a0289fc6f692f265cbaadd6c8b3
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));


        String commentUser = comment.getCommentUser();
        comment.setCommentContents(commentRequestDto.getCommentContents());


        // 댓글 쓴 사람이 로그인한 사람인지 확인
        if(comment.getCommentUser().equals(userDetails.getUsername())){
<<<<<<< HEAD
            Comment saveComment = commentRepository.save(comment);
            HashMap<String, Long> responseId = new HashMap<>();
            responseId.put("commentId", saveComment.getId());
            return responseId;
=======
            commentRepository.save(comment);

>>>>>>> bc62a1376e5a4a0289fc6f692f265cbaadd6c8b3
        } else{
            throw new IllegalArgumentException("댓글 수정에 실패하였습니다.");

        }
    }

    public HashMap<String, Long> deleteComment(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

        if(comment.getCommentUser().equals(userDetails.getUsername())){
            HashMap<String, Long> responseId = new HashMap<>();
            responseId.put("commentId", comment.getId());
            commentRepository.deleteById(commentId);
            return responseId;
        } else{
            throw new IllegalArgumentException("댓글 삭제에 실패하였습니다.");
        }
    }
}
