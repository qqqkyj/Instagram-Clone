package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    @Transactional
    public PostResponse create(PostCreateRequest postCreateRequest, Long userId) {
        User user = userService.findById(userId);
        Post post = Post.builder()
                .content(postCreateRequest.getContent())
                .user(user)
                .build();
        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    @Override
    public Post findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("post not found"));
        return post;
    }

    @Override
    public PostResponse getPostById(Long id) {
        Post post = findById(id);
        return PostResponse.from(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponse::from).toList();
    }
}
