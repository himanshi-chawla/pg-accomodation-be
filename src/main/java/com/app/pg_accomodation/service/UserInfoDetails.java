package com.app.pg_accomodation.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.pg_accomodation.entity.User;

public class UserInfoDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(User user) {
        this.username = user.getUsername(); // Fetching username
        this.email = user.getEmail(); // Storing email for additional verification
        this.password = user.getPassword();
        this.authorities = List.of(user.getRole().split(",")).stream()
                .map(String::trim) // Trim whitespace
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username; // Returning username for authentication
    }

    public String getEmail() {
        return email; // Getter for email
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
