package Practice1.hotelapp.auth;

import Practice1.hotelapp.dto.LoginRequestDTO;
import Practice1.hotelapp.dto.LoginResponseDTO;
import Practice1.hotelapp.security.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        UserDetails user =  (UserDetails) authentication.getPrincipal();

        String token = JwtUtil.generateJwtToken(
                user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority()
        );
        return new LoginResponseDTO(token);
    }
}
