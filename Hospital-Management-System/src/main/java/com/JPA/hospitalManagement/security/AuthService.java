package com.JPA.hospitalManagement.security;

import com.JPA.hospitalManagement.dto.LoginRequestDto;
import com.JPA.hospitalManagement.dto.LoginResponseDto;
import com.JPA.hospitalManagement.dto.SignupResponseDto;
import com.JPA.hospitalManagement.entity.User;
import com.JPA.hospitalManagement.entity.type.AuthProviderType;
import com.JPA.hospitalManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user =  (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }
}
