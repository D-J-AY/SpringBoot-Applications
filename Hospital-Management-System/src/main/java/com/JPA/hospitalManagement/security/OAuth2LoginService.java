package com.JPA.hospitalManagement.security;


import com.JPA.hospitalManagement.dto.LoginRequestDto;
import com.JPA.hospitalManagement.dto.LoginResponseDto;
import com.JPA.hospitalManagement.dto.SignupResponseDto;
import com.JPA.hospitalManagement.entity.User;
import com.JPA.hospitalManagement.entity.type.AuthProviderType;
import com.JPA.hospitalManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User signUpInternal(LoginRequestDto signupRequestDto, AuthProviderType authProviderType,String providerId) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (user != null) throw new IllegalArgumentException("Username is already taken");
         user = User.builder()
                .username(signupRequestDto.getUsername())
                 .providerId(providerId)
                 .providerType(authProviderType)
                .build();

         if(authProviderType == AuthProviderType.EMAIL){
             user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
         }

         return userRepository.save(user);
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL,null);
        return new SignupResponseDto(user.getId(), user.getUsername());

    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
        AuthProviderType providerType = authUtil.getProviderType(registrationId);
        String providerId = authUtil.getProviderIdFromOAuth2User(oAuth2User, registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        User emailUser = userRepository.findByUsername(email).orElse(null);

        if (emailUser == null && user == null) {
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            user = signUpInternal(new LoginRequestDto(username, null), providerType, providerId);
        } else if (user != null) {
            if (email != null && !email.isBlank() && !email.equals(user.getUsername())) {
                user.setUsername(email);
                userRepository.save(user);
            }
        } else {
            throw new BadCredentialsException("This email is already registered by provider: " + emailUser.getProviderType());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }
}
