package com.example.instagram.controller;

import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

//    private final PostService postService;

//    @GetMapping("/")
//    public String home(Model model) {
//        List<PostResponse> postResponses = postService.getAllPosts();
//        List<PostResponse> postResponses = postService.getAllPostsWithStats();
//        model.addAttribute("posts", postResponses);
//        return "home";
//    }

        @GetMapping("/")
        public String home(){
            return "home";
        }
}
