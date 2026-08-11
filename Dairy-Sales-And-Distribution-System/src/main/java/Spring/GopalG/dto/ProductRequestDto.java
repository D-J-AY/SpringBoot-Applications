package Spring.GopalG.dto;


import Spring.GopalG.entity.Shop;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String productName;

    private  String productDescription;

    @NotBlank(message = "Product unit is required")
    private String productUnit;
}
