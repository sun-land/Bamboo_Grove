package com.sparta.team6project.service;


import com.sparta.team6project.dto.CommentRequestDto;
import com.sparta.team6project.exception.CommentResponse;
import com.sparta.team6project.model.Comment;
import com.sparta.team6project.model.Post;
import com.sparta.team6project.repository.CommentRepository;
import com.sparta.team6project.repository.PostRepository;
import com.sparta.team6project.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public void createComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(commentRequestDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("게시글이 존재하지 않습니다."));
        comment.setCommentUser(userDetails.getUsername());
        comment.setPost(post);

        String commentUser = comment.getCommentUser();

        if(commentUser==null || commentUser==""){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        } else{
            commentRepository.save(comment);
        }
    }


    public void updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

        String commentUser = comment.getCommentUser();
        comment.setCommentContents(commentRequestDto.getCommentContents());


        // 댓글 쓴 사람이 로그인한 사람인지 확인
        if(comment.getCommentUser().equals(userDetails.getUsername())){
            commentRepository.save(comment);
        } else{
            throw new IllegalArgumentException("댓글 수정에 실패하였습니다.");
        }
    }

    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));

        if(comment.getCommentUser().equals(userDetails.getUsername())){
            commentRepository.deleteById(commentId);
        } else{
            throw new IllegalArgumentException("댓글 삭제에 실패하였습니다.");
        }
    }
}
