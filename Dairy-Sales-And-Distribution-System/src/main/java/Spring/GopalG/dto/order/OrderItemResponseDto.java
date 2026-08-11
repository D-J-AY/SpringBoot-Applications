package Spring.GopalG.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {
    private int orderItemId;
    private int orderId;
    private int variantId;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal subTotal;
}

