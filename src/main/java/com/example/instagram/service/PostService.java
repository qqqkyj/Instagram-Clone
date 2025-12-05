package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostResponse create(PostCreateRequest postCreateRequest, MultipartFile image, Long  userId);
    Post findById(Long id);
    PostResponse getPostById(Long id);
    List<PostResponse> getAllPosts();
    List<PostResponse> getPostsByUsername(String username);
    long countByUserId(Long userId);
    List<PostResponse> getAllPostsWithStats();

    //피드 조회
    Slice<PostResponse> getFeedPosts(Long userId, Pageable pageable);

    //전체 게시물 조회
    Slice<PostResponse> getAllPostsPaging(Pageable pageable);

    //검색
    Slice<PostResponse> searchPosts(String keyword, Pageable pageable);

    //게시물 수정
    void update(PostCreateRequest postCreateRequest, MultipartFile image);
}
