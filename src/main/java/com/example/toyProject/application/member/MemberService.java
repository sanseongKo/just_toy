package com.example.toyProject.application.member;

import com.example.toyProject.domain.member.Member;
import com.example.toyProject.domain.member.role.Role;
import com.example.toyProject.repository.member.MemberRepository;
import com.example.toyProject.config.security.jwt.JwtProvider;
import com.example.toyProject.config.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    public TokenInfo login(String memberId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtProvider.generateToken(authentication);
    }

    public Member signup(String memberId, String password) {
        Member member = Member.builder()
                .memberId(memberId)
                .password(password)
                .build();

        member.addRole(Role.PLAYER);

        memberRepository.save(member);

        return member;
    }
}
