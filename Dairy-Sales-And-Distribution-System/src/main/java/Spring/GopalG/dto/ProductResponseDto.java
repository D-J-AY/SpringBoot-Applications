package Spring.GopalG.dto;


import Spring.GopalG.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class ProductResponseDto {
    private int productId;
    private String productName;
    private String description;
    private String productUnit;
    private Boolean activeStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
