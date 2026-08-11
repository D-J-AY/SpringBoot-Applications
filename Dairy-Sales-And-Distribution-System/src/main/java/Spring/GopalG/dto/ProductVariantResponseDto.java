package Spring.GopalG.dto;

import Spring.GopalG.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class ProductVariantResponseDto {

    private int id;
    private int productId;
    private BigDecimal packSize;
    private String unit;
    private BigDecimal pricePerPack;
    private Boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}