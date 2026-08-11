package Spring.GopalG.dto.order;

import Spring.GopalG.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusUpdateDto {
    private PaymentStatus paymentStatus;
}
