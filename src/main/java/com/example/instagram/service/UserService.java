package com.example.instagram.service;

import com.example.instagram.dto.request.SignUpRequest;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.entity.User;

import java.util.List;

public interface UserService {
    User register(SignUpRequest signUpRequest);

    boolean existsByUsername(String username);

    User findById(Long id);

    User findByUsername(String username);

    UserResponse getUserById(Long id);

    //사용자 검색
    List<UserResponse> searchUsers(String keyword);
}
