package com.example.instagram.controller;

import com.example.instagram.dto.request.CommentCreateRequest;
import com.example.instagram.dto.request.PostCreateRequest;
import com.example.instagram.dto.response.CommentResponse;
import com.example.instagram.dto.response.PostResponse;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.CommentService;
import com.example.instagram.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

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
        List<CommentResponse> comments = commentService.getAllCommentsByPostId(id);
        model.addAttribute("post", post);
        model.addAttribute("commentRequest", new CommentCreateRequest());
        model.addAttribute("comments", comments);
        return "post/detail";
    }

    @PostMapping("/{postId}/comments")
    public String createComment(@PathVariable Long postId,
                                @Valid @ModelAttribute CommentCreateRequest commentCreateRequest,
                                BindingResult bindingResult,
                                //세션을 통해 현재 로그인한 사용자 정보
                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (bindingResult.hasErrors()) {
            return "post/detail";
        }

        commentService.create(postId, commentCreateRequest, userDetails.getId());

        return "redirect:/posts/"+postId;
    }
}
