package com.sparta.team6project.repository;

import com.sparta.team6project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllOrderByCreatedAtDesc();
}
