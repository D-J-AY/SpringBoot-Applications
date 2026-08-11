package Spring.GopalG.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopRequestDto {

    @NotBlank(message = "Shop Name is required")
    private String shopName;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Number should be of 10 digits")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;
}
