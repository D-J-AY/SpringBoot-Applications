package Spring.GopalG.service;

import Spring.GopalG.dto.login.LoginRequestDto;
import Spring.GopalG.dto.login.RegistrationRequestDto;
import Spring.GopalG.dto.login.SendOtpDto;
import Spring.GopalG.dto.login.VerifyOtpDto;
import Spring.GopalG.entity.OtpVerification;
import Spring.GopalG.entity.User;
import Spring.GopalG.enums.Role;
import Spring.GopalG.exception.BadRequestException;
import Spring.GopalG.exception.ResourceNotFoundException;
import Spring.GopalG.repository.OtpRepository;
import Spring.GopalG.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, OtpRepository otpRepository) {
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String sendOtp(SendOtpDto sendOtpDto) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        System.out.println("otp: " + otp);

        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setPhone(sendOtpDto.getPhoneNumber());
        otpEntity.setOtp(otp);
        otpEntity.setVerified(false);
        otpEntity.setExpiresAt(LocalDateTime.now().plusDays(2));
        otpRepository.save(otpEntity);

        return "Otp Send";
    }

    public String verifyOtp(VerifyOtpDto verifyOtpDto) {
        OtpVerification savedOtp = otpRepository.findByPhone(verifyOtpDto.getPhoneNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Otp not found"));

        if(savedOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Otp expired");
        }

        if(!savedOtp.getOtp().equals(verifyOtpDto.getOtp())) {
            throw new RuntimeException("Otp not matched");
        }

        savedOtp.setVerified(true);
        otpRepository.save(savedOtp);

        return "OTP verified successfully";
    }

    public String register(RegistrationRequestDto dto){
        OtpVerification otp = otpRepository.findByPhone(dto.getPhoneNumber())
                .orElseThrow(()-> new ResourceNotFoundException("Otp not found"));

        if(!otp.isVerified()) {
            throw new RuntimeException("Otp not verified");
        }

        User user = new User();

        user.setPhoneNumber(dto.getPhoneNumber());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole(Role.USER);

        user.setPhoneVerified(true);

        user.setActiveStatus(true);

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequestDto dto) {
        User user = userRepository.findByPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword()
        )){
            throw new BadRequestException("Wrong password");
        }

        return "User logged successfully";
    }
}
