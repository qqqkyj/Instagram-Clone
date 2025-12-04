package com.example.instagram.service;

import com.example.instagram.dto.request.CommentRequest;
import com.example.instagram.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse create(Long postId, CommentRequest commentCreateRequest, Long userId);
    List<CommentResponse> getAllCommentsByPostId(Long postId);
}
