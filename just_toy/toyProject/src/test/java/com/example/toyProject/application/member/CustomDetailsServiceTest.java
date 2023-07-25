package com.example.toyProject.application.member;

import com.example.toyProject.domain.member.Member;
import com.example.toyProject.repository.member.MemberRepository;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomDetailsServiceTest {
    @Mock
    private MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private CustomDetailsService customDetailsService;

    @BeforeEach
    void setup() {
        customDetailsService = new CustomDetailsService(memberRepository, passwordEncoder);
    }

    @DisplayName("유저가 없다면 유저 없음 이라는 메세지를 포함한 에러가 발생해야 한다.")
    @Test
    void loadNotFoundUserTest() {
        //given
        String username = "sanseong";
        Member member = null;
        Optional<Member> memberAnswer = Optional.ofNullable(member);
        when(memberRepository.findByMemberId(username)).thenReturn(memberAnswer);
        //then
        assertThatThrownBy(() ->
                customDetailsService.loadUserByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class);
    }

    @DisplayName("유저를 찾는 것에 문제가 없다면 UserDetail을 리턴해야한다.")
    @Test
    void loadUserTest() {
        //given
        String username = "sanseong";
        Member member = Member.builder()
                .memberId(username)
                .password("1234")
                .roles(List.of(Member.Role.PLAYER))
                .build();
        Optional<Member> memberAnswer = Optional.of(member);
        when(memberRepository.findByMemberId(username)).thenReturn(memberAnswer);

        //when
        UserDetails userDetails = customDetailsService.loadUserByUsername(username);

        //then
        assertThat(userDetails.getUsername()).isEqualTo("sanseong");
    }
}