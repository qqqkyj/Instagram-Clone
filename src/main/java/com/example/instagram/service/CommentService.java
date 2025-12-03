package com.example.instagram.service;

import com.example.instagram.dto.request.CommentCreateRequest;
import com.example.instagram.dto.response.CommentResponse;

public interface CommentService {
    CommentResponse create(Long postId, CommentCreateRequest commentCreateRequest, Long userId);
}
