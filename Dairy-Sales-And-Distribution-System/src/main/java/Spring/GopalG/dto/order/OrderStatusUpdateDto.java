package Spring.GopalG.dto.order;

import Spring.GopalG.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusUpdateDto {
    private OrderStatus orderStatus;
}
