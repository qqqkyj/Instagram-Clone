package com.example.instagram.service;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.exception.BusinessException;
import com.example.instagram.exception.ErrorCode;
import com.example.instagram.repository.CommentRepository;
import com.example.instagram.repository.FollowRepository;
import com.example.instagram.repository.LikeRepository;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
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
    private final FollowRepository followRepository;

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
        Post post = postRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));
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

    //피드 조회
    @Override
    public Slice<PostResponse> getFeedPosts(Long userId, Pageable pageable) {
        List<Long> followingIds = followRepository.findFollowingIdsByFollowerId(userId);
        Slice<Post> posts = postRepository.findFeedPostsByUserIds(followingIds, pageable);
        List<PostResponse> content = posts.getContent().stream()
                .map(post -> {
                    long likeCount = likeRepository.countByPostId(post.getId());
                    long commentCount = commentRepository.countByPostId(post.getId());
                    return PostResponse.from(post, commentCount, likeCount);
                })
                .toList();
        return new SliceImpl<>(content, pageable, posts.hasNext());
    }

    //전체 게시물 조회
    @Override
    public Slice<PostResponse> getAllPostsPaging(Pageable pageable) {
        Slice<Post> posts = postRepository.findAllWithUserPaging(pageable);
        List<PostResponse> content = posts.getContent().stream()
                .map(post -> {
                    long likeCount = likeRepository.countByPostId(post.getId());
                    long commentCount = commentRepository.countByPostId(post.getId());
                    return PostResponse.from(post, commentCount, likeCount);
                })
                .toList();
        return new SliceImpl<>(content, pageable, posts.hasNext());
    }

    @Override
    public Slice<PostResponse> searchPosts(String keyword, Pageable pageable) {
        Slice<Post> posts = postRepository.searchByKeyword(keyword, pageable);
        List<PostResponse> content = posts.getContent().stream()
                .map(post -> {
                    long likeCount = likeRepository.countByPostId(post.getId());
                    long commentCount = commentRepository.countByPostId(post.getId());
                    return PostResponse.from(post, commentCount, likeCount);
                })
                .toList();
        return new SliceImpl<>(content, pageable, posts.hasNext());
    }
}
