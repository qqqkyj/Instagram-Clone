package com.example.instagram.controller;

import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.PostService;
import com.example.instagram.service.PostServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postCreateRequest", new PostCreateRequest());
        return "post/form";
    }

    //현재 로그인한 사용자 정보 함께 넘기기
    @PostMapping
    public String create(@Valid @ModelAttribute PostCreateRequest postCreateRequest,
                         BindingResult bindingResult,
                         //세션을 통해 현재 로그인한 사용자 정보
                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            return "post/form";
        }
        postService.create(postCreateRequest, userDetails.getId());
        return "redirect:/";
    }

    //상세페이지
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostResponse post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post/detail";
    }
}
