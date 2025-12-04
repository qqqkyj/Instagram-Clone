package com.example.instagram.service;

import com.example.instagram.dto.request.ProfileUpdateRequest;
import com.example.instagram.dto.response.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileResponse getProfile(String username);
    void updateProfile(Long id, ProfileUpdateRequest profileUpdateRequest, MultipartFile profileImg);
}
