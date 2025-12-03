package com.example.instagram.service;

public interface FollowService {
    void toggleFollow(Long followerId, String followingUsername);
}
