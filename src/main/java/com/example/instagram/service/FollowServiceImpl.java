package com.example.instagram.service;

import com.example.instagram.entity.Follow;
import com.example.instagram.entity.Like;
import com.example.instagram.entity.Post;
import com.example.instagram.entity.User;
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

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();
        followRepository.save(follow);
    }
}
