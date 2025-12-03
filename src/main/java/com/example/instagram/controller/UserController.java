package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.dto.response.ProfileResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.FollowService;
import com.example.instagram.service.PostService;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    //사용자 프로필
    @GetMapping("/{username}")
    public String profile(@PathVariable String username,
                          Model model,
                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        ProfileResponse profile = userService.getProfile(username);
        List<PostResponse> posts = postService.getPostsByUsername(username);

        model.addAttribute("profile", profile);
        model.addAttribute("posts", posts);
        return "user/profile";
    }

    //팔로우
    @PostMapping("/{username}/follow")
    public String toggleFollow(@PathVariable String username,//following
                               //현재 로그인한 사용자(follower)
                               @AuthenticationPrincipal CustomUserDetails userDetails){

        followService.toggleFollow(userDetails.getId(), username);
        return "redirect:/users/" + username;
    }
}
