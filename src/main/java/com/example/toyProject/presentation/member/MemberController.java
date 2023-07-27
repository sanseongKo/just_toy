package com.example.toyProject.presentation.member;

import com.example.toyProject.application.member.MemberService;
import com.example.toyProject.presentation.member.request.SignupRequestBody;
import com.example.toyProject.presentation.member.request.LoginRequestBody;
import com.example.toyProject.config.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginRequestBody loginRequestBody) {
        String memberId = loginRequestBody.memberId();
        String password = loginRequestBody.password();

        return memberService.login(memberId, password);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestBody signupRequestBody) {
        return memberService.signup(signupRequestBody.memberId(), signupRequestBody.password())
                .getMemberId();
    }
}
