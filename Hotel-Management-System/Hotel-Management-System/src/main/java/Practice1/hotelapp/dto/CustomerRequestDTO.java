package Practice1.hotelapp.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank(message="First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Email(message = "Invalid Email format")
    @NotBlank
    private String email;

    @Size(min=10,max=10)
    @NotBlank
    private String phone;

    @NotBlank
    private String city;
}
