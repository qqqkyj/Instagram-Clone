package com.example.instagram.controller;

import com.example.instagram.dto.request.PostCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "post/form";
    }
}
