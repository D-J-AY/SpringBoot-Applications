package Spring.GopalG.controller;

import Spring.GopalG.dto.login.LoginRequestDto;
import Spring.GopalG.dto.login.RegistrationRequestDto;
import Spring.GopalG.dto.login.SendOtpDto;
import Spring.GopalG.dto.login.VerifyOtpDto;
import Spring.GopalG.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestBody SendOtpDto sendOtpDto) {
        return authService.sendOtp(sendOtpDto);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto) {
        return authService.verifyOtp(verifyOtpDto);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequestDto registrationRequestDto) {
        return authService.register(registrationRequestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}


