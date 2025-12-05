package com.example.instagram.service;

import com.example.instagram.dto.request.ProfileUpdateRequest;
import com.example.instagram.dto.request.SignUpRequest;
import com.example.instagram.dto.response.ProfileResponse;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.entity.Role;
import com.example.instagram.entity.User;
import com.example.instagram.exception.BusinessException;
import com.example.instagram.exception.ErrorCode;
import com.example.instagram.repository.FollowRepository;
import com.example.instagram.repository.PostRepository;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(SignUpRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .role(Role.USER)
                .name(signUpRequest.getName())
                .build();
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return UserResponse.from(findById(id));
    }

    @Override
    public List<UserResponse> searchUsers(String keyword) {
        return userRepository.searchByKeyword(keyword).stream()
                .map(UserResponse::from)
                .toList();
    }
}
