package com.example.toyProject.presentation.member;

import com.example.toyProject.application.member.MemberService;
import com.example.toyProject.application.member.dto.SignUpDto;
import com.example.toyProject.presentation.member.request.LoginReuqestBody;
import com.example.toyProject.config.security.jwt.TokenInfo;
import com.example.toyProject.presentation.member.request.SignUpRequestBody;
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
    public TokenInfo login(@RequestBody LoginReuqestBody requestBody) {
        String memberId = requestBody.getMemberId();
        String password = requestBody.getPassword();

        return memberService.login(memberId, password);
    }

    @PostMapping("/signup")
    public void singUp(@RequestBody SignUpRequestBody requestBody) {
        SignUpDto dto = requestBody.toDto();

        memberService.signUp(dto);
    }
}
