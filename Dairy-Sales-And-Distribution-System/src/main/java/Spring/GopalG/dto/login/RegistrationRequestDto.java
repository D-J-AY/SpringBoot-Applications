package Spring.GopalG.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDto {
    private String shopName;
    private String address;
    private String phoneNumber;
    private String password;
}
