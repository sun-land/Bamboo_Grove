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

    public CommentResponse createComment(Long postId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(commentRequestDto);
        CommentResponse commentResponse = null;
        Post post = postRepository.findById(postId).orElseThrow(() -> new NullPointerException("게시글이 존재하지 않습니다."));
        comment.setCommentUser(userDetails.getUsername());
        comment.setPost(post);

        String commentUser = comment.getCommentUser();

        if(commentUser==null || commentUser==""){
            throw new IllegalArgumentException("로그인이 필요합니다.");
        } else{
            commentRepository.save(comment);
            commentResponse.setOk("TRUE");
            commentResponse.setMessage("댓글생성 성공");
            return commentResponse;
        }
    }


    public CommentResponse updateComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(commentRequestDto);
        CommentResponse commentResponse = null;
        Post post = postRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));
        comment.setCommentUser(userDetails.getUsername());
        comment.setPost(post);

        String commentUser = comment.getCommentUser();

        if(commentUser==null || commentUser==""){
            throw new IllegalArgumentException("댓글 수정에 실패하였습니다.");
        } else{
            commentRepository.save(comment);
            commentResponse.setOk("TRUE");
            commentResponse.setMessage("댓글수정 성공");
            return commentResponse;
        }
    }

    public CommentResponse deleteComment(Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(commentRequestDto);
        CommentResponse commentResponse = null;
        Post post = postRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다."));
        comment.setCommentUser(userDetails.getUsername());
        comment.setPost(post);

        String commentUser = comment.getCommentUser();

        if(post.getPostUser()==commentUser){
            commentRepository.delete(comment);
            commentResponse.setOk("TRUE");
            commentResponse.setMessage("댓글삭제제 성공");
           return commentResponse;
        } else{
            throw new IllegalArgumentException("댓글 삭제에 실패하였습니다.");
        }
    }
}
