package Spring.GopalG.dto.order;

import Spring.GopalG.enums.OrderStatus;
import Spring.GopalG.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private int orderId;
    private String orderNumber;
    private int shopId;
    private Timestamp orderDate;
    private LocalDate deliveryDate;
    private BigDecimal totalAmount;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderItemResponseDto> orderItems;
}

