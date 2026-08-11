package Spring.GopalG.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpDto {
    private String phoneNumber;
    private String otp;
}
