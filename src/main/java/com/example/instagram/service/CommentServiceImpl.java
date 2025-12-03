package com.example.instagram.service;

import com.example.instagram.dto.request.CommentCreateRequest;
import com.example.instagram.dto.response.CommentResponse;
import com.example.instagram.entity.Comment;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    @Transactional
    @Override
    public CommentResponse create(Long postId,
                                  CommentCreateRequest commentCreateRequest,
                                  Long userId) {
        Post post = postService.findById(postId);
        User user = userService.findById(userId);
        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(commentCreateRequest.getContent())
                .build();
        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);
    }
}
