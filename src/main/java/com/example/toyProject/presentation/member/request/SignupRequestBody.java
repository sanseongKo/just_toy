package com.example.toyProject.presentation.member.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SignupRequestBody {
    private final String memberId;
    private final String password;
}
