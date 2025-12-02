package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;

import java.util.List;

public interface PostService {
    PostResponse create(PostCreateRequest postCreateRequest, Long  userId);
    Post findById(Long id);
    PostResponse getPostById(Long id);
    List<PostResponse> getAllPosts();
}
