package com.example.instagram.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(min = 1, max = 1000, message = "내용은 1000자 이내로 입력해 주세요.")
    private String content;
}
