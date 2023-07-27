package com.example.toyProject.presentation.member.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestBody {
    private String memberId;
    private String password;
}
