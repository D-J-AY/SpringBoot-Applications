package com.JPA.hospitalManagement.controller;

import com.JPA.hospitalManagement.dto.LoginRequestDto;
import com.JPA.hospitalManagement.dto.LoginResponseDto;
import com.JPA.hospitalManagement.dto.SignupResponseDto;
import com.JPA.hospitalManagement.security.AuthService;
import com.JPA.hospitalManagement.security.OAuth2LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OAuth2LoginService oAuth2LoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupRequestDto) {
        return ResponseEntity.ok(oAuth2LoginService.signup(signupRequestDto));
    }
}
