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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomDetailsServiceTest {
    String username = "sanseong";

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
    void loadUserExceptionTest() {
        Optional<Member> mockResult = Optional.empty();

        when(memberRepository.findByMemberId(username)).thenReturn(mockResult).getMock();

        assertThatThrownBy(() ->
                customDetailsService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @DisplayName("회원이라면 회원 정보가 리턴 되어야 한다")
    @Test
    void loadUserTest() {
        Optional<Member> member = Optional.of(
                Member.builder()
                .memberId(username)
                .password("1234")
                .build()
        );

        when(memberRepository.findByMemberId(username)).thenReturn(member);

        UserDetails userDetails = customDetailsService.loadUserByUsername(username);

        assertThat(userDetails.getUsername()).isEqualTo(username);
    }

}