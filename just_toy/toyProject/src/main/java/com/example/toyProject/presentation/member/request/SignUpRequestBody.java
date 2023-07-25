package com.example.toyProject.presentation.member.request;

import com.example.toyProject.application.member.dto.SignUpDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignUpRequestBody {
    private final String memberId;

    private final String password;

    public SignUpDto toDto() {
        return new SignUpDto(this.memberId, this.password);
    }
}
