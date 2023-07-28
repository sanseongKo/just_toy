package com.example.toyProject.application.member;

import com.example.toyProject.domain.member.Member;
import com.example.toyProject.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomDetailsServiceTest {
    private CustomDetailsService customDetailsService;
    @Mock
    private MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        customDetailsService = new CustomDetailsService(memberRepository, passwordEncoder);
    }

    @DisplayName("회원이 아니라면 에러가 발생한다.")
    @Test
    void loadUserTest() {
        String username = "sanseong";
        Optional<Member> mockResult = Optional.empty();

        when(memberRepository.findByMemberId(username)).thenReturn(mockResult).getMock();

        Assertions.assertThatThrownBy(() ->
                customDetailsService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }

}