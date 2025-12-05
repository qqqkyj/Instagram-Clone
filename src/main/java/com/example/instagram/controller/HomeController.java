package com.example.instagram.controller;

import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/home-legacy")
    public String home(Model model) {
//        List<PostResponse> postResponses = postService.getAllPosts();
        List<PostResponse> postResponses = postService.getAllPostsWithStats();
        model.addAttribute("posts", postResponses);
        return "home-legacy";
    }

        @GetMapping("/")
        public String home(){
            return "home";
        }

        @GetMapping("/explore")
        public String explore(){
            return "explore";
        }

        @GetMapping("/search")
        public String search(){
            return "search";
        }
}
