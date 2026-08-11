package Spring.GopalG.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVariantUpdateDto {

    @NotBlank(message = "Product Name is required")
    private String productName;
    private String productDescription;

    @NotBlank(message = "Product Unit is required")
    private String productUnit;
}
