package Spring.GopalG.dto;


import Spring.GopalG.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductVariantRequestDto {
        private int productId;
        private BigDecimal packSize;
        private String unit;
        private BigDecimal pricePerPack;
}
