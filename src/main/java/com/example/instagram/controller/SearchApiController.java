package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.service.PostService;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchApiController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/users")
    public List<UserResponse> searchUsers(
            @RequestParam String q
    ){
        return userService.searchUsers(q.trim());
    }

//    @GetMapping("/posts")
//    public Slice<PostResponse> searchPosts(){
//
//    }
}
