package com.example.instagram.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequest {

    @NotBlank(message = "사용자명을 입력해 주세요.")
    @Size(min = 3, max = 20, message = "사용자명은 3 ~20글자까지 가능합니다.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 4, message = "최소 4글자 이상 입력해 주세요.")
    private String password;
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email
    private String email;
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;
}
