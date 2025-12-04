package com.example.instagram.repository;

import com.example.instagram.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    //전체 게시물 조회할 떄 user정보를 담아서 오도록
    @EntityGraph(attributePaths = {"user"})
    List<Post> findAllByOrderByCreatedAtDesc();

    @EntityGraph(attributePaths = {"user"})
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    long countByUserId(Long userId);

    @EntityGraph(attributePaths = {"user"})
    Optional<Post> findById(Long id);
}
