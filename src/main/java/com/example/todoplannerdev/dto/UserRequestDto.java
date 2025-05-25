package com.example.todoplannerdev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    @Size(max = 4, message = "이름은 4자 이내로 입력해주세요")
    private String name;
    @NotBlank(message = "이메일은 필수 입력값입니다")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
            message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 40, message = "이메일 40자 이내로 입력해주세요")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Size(max = 40, message = "비밀번호 40자 이내로 입력해주세요")
    private String password;
}
