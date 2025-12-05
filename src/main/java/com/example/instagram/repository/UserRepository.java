package com.example.instagram.repository;

import com.example.instagram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    //검색 기능
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.name LIKE %:keyword%")
    List<User> searchByKeyword(@Param("keyword") String keyword);
}
