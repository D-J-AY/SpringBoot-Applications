package Practice1.hotelapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
}

