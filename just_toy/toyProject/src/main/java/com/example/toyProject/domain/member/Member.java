package com.example.toyProject.domain.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "members")
public class Member implements UserDetails {
    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    public void addPlayerRole() {
        this.roles.add(Role.PLAYER);
    }

    public void addManagerRole() {
        this.roles.add(Role.MANAGER);
    }

    public List<String> toRoleList() {
        return roles.stream().map(Role::getRole).toList();
    }

    private Member(String memberId, String password, List<Role> roles) {
        this.memberId = memberId;
        this.password = password;
        this.roles = roles;
    }

    public enum Role {
        PLAYER("player"),
        MANAGER("manager");

        private final String role;

        Role(String role) {
            this.role = role;
        }

        public String getKey() {
            return name();
        }

        public String getRole() {
            return role;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
