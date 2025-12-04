package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.repository.CommentRepository;
import com.example.instagram.repository.LikeRepository;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public PostResponse create(PostCreateRequest postCreateRequest, MultipartFile image, Long userId) {
        User user = userService.findById(userId);

        //게시판 이미지 업로드
        String imageUrl = fileService.fileUpload(image);

        Post post = Post.builder()
                .content(postCreateRequest.getContent())
                .user(user)
                .imageUrl(imageUrl)
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

    @Override
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userService.findByUsername(username);
        return postRepository.findByUserIdOrderByCreatedAtDesc(user.getId()).stream().map(PostResponse::from).toList();
    }

    @Override
    public long countByUserId(Long userId) {
        return postRepository.countByUserId(userId);
    }

    @Override
    public List<PostResponse> getAllPostsWithStats() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> {
                    long likeCount = likeRepository.countByPostId(post.getId());
                    long commentCount = commentRepository.countByPostId(post.getId());
                    return PostResponse.from(post, commentCount, likeCount);
                })
                .toList();
    }
}
