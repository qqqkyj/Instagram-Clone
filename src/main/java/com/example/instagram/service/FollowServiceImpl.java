package com.example.instagram.service;

import com.example.instagram.entity.Follow;
import com.example.instagram.entity.Like;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
import com.example.instagram.exception.BusinessException;
import com.example.instagram.exception.ErrorCode;
import com.example.instagram.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;
    private final UserService userService;

    @Transactional
    @Override
    public void toggleFollow(Long followerId, String followingUsername) {
        User follower = userService.findById(followerId);
        User following = userService.findByUsername(followingUsername);
        //자기 자신 팔로우 방지
        if(follower.getId() == following.getId()){
            throw new BusinessException(ErrorCode.SELF_FOLLOW);
        }
        //팔로우 토글
        Optional<Follow> existingFollow = followRepository
                .findByFollowerIdAndFollowingId(follower.getId(), following.getId());
        if(existingFollow.isPresent()){
            followRepository.delete(existingFollow.get());
        }
        else{
            Follow follow = Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build();
            followRepository.save(follow);
        }
    }

    @Override
    public boolean isFollowing(Long followerId, Long followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    @Override
    public long countByFollowerId(Long followerId) {
        return followRepository.countByFollowerId(followerId);
    }

    @Override
    public long countByFollowingId(Long followingId) {
        return followRepository.countByFollowingId(followingId);
    }
}
