package com.example.instagram.service;

import com.example.instagram.dto.request.ProfileUpdateRequest;
import com.example.instagram.dto.response.ProfileResponse;
import com.example.instagram.entity.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;
    private final FileService fileService;

    @Override
    public ProfileResponse getProfile(String username) {
        User user = userService.findByUsername(username);
        long postCount = postService.countByUserId(user.getId());
        long followerCount = followService.countByFollowingId(user.getId());
        long followingCount = followService.countByFollowerId(user.getId());
        return ProfileResponse.from(user, postCount, followerCount, followingCount);
    }

    @Override
    @Transactional
    public void updateProfile(Long id, ProfileUpdateRequest profileUpdateRequest, MultipartFile profileImg) {
        User user = userService.findById(id);

        //프로필 이미지 처리
        String imgUrl = fileService.fileUpload(profileImg);
        if (imgUrl != null) {
            user.updateProfileImage(imgUrl);
        }
        user.updateProfile(profileUpdateRequest.getName(), profileUpdateRequest.getBio());
    }
}
