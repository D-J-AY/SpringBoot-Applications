package Spring.GopalG.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private int shopId;
    private LocalDate deliveryDate;
    private List<OrderItemRequestDto> orderItems;
}
