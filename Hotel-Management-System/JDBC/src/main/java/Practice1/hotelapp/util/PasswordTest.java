package Practice1.hotelapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "user123";

        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
