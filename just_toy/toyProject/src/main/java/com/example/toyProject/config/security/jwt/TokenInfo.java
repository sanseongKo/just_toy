package com.example.toyProject.config.security.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
