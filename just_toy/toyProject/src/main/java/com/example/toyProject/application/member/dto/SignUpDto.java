package com.example.toyProject.application.member.dto;

import com.example.toyProject.domain.member.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignUpDto {
    private final String memberId;

    private final String password;

    public Member toEntity() {
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .build();
    }
}
