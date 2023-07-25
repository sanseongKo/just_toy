package com.example.toyProject.presentation.member;

import com.example.toyProject.application.member.MemberService;
import com.example.toyProject.presentation.member.request.LoginDto;
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
    public TokenInfo login(@RequestBody LoginDto loginDto) {
        String memberId = loginDto.getMemberId();
        String password = loginDto.getPassword();

        return memberService.login(memberId, password);
    }

    @PostMapping("/test")
    public void test(
            @RequestHeader String authorization
    ) {
        log.info(authorization);
    }
}
