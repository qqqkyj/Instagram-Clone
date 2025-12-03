package com.example.instagram.service;

import com.example.instagram.entity.Like;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    @Transactional
    @Override
    public void toggleLike(Long postId, Long userId) {
        Optional<Like> existLike = likeRepository.findByPostIdAndUserId(postId, userId);
        if(existLike.isPresent()){//좋아요 존재
            likeRepository.delete(existLike.get());//삭제
        }
        else{//없으면
            Post post = postService.findById(postId);
            User user = userService.findById(userId);
            Like like = Like.builder()
                    .post(post)
                    .user(user)
                    .build();
            likeRepository.save(like);//생성
        }
    }

    @Override
    public boolean isLiked(Long postId, Long userId) {
        return likeRepository.findByPostIdAndUserId(postId, userId).isPresent();
    }

    @Override
    public long getLikeCount(Long postId) {
        return 0;
    }
}
