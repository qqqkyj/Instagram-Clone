package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @GetMapping("/feed")
    public Slice<PostResponse> getFeed(@AuthenticationPrincipal CustomUserDetails userDetails,
                                       @PageableDefault(size = 5) Pageable pageable){
        return postService.getFeedPosts(userDetails.getId(), pageable);
    }

    @GetMapping("/explore")
    public Slice<PostResponse> getExplore(@PageableDefault(size = 12) Pageable pageable){
        return postService.getAllPostsPaging(pageable);
    }
}
