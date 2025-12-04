package com.example.instagram.repository;

import com.example.instagram.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Comment> findAllByPostIdOrderByCreatedAtDesc(Long postId);
    long countByPostId(Long postId);
}
