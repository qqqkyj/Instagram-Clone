package com.example.instagram.controller;

import com.example.instagram.dto.request.ProfileUpdateRequest;
import com.example.instagram.dto.response.UserResponse;
import com.example.instagram.entity.User;
import com.example.instagram.security.CustomUserDetails;
import com.example.instagram.service.ProfileService;
import com.example.instagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @GetMapping("/edit")
    public String editForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponse currentUser = userService.getUserById(userDetails.getId());
        ProfileUpdateRequest profileUpdateRequest = new ProfileUpdateRequest();
        profileUpdateRequest.setBio(currentUser.getBio());
        profileUpdateRequest.setName(currentUser.getName());

        model.addAttribute("profileUpdateRequest", profileUpdateRequest);
        model.addAttribute("currentUser", currentUser);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute ProfileUpdateRequest profileUpdateRequest,
                       BindingResult bindingResult,//반드시 검증 대상 뒤에 와야 유효성 체크 가능
                       @AuthenticationPrincipal CustomUserDetails userDetails,
                       Model model,
                       @RequestParam(value = "profileImg", required = false)MultipartFile profileImg){
        if(bindingResult.hasErrors()){
            UserResponse currentUser = userService.getUserById(userDetails.getId());
            model.addAttribute("currentUser", currentUser);
            return "profile/edit";
        }

        profileService.updateProfile(userDetails.getId(), profileUpdateRequest, profileImg);

        return "redirect:/users/" + userDetails.getUsername();
    }
}
