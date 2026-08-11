package Spring.GopalG.controller;

import Spring.GopalG.dto.order.OrderRequestDto;
import Spring.GopalG.dto.order.OrderResponseDto;
import Spring.GopalG.dto.order.OrderStatusUpdateDto;
import Spring.GopalG.dto.order.PaymentStatusUpdateDto;
import Spring.GopalG.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDto> getOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public List<OrderResponseDto> getOrdersByShop(@PathVariable int id) {
        return orderService.getOrdersByShopId(id);
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @PatchMapping("/{id}/payment-status")
    public OrderResponseDto updatePaymentStatus(@PathVariable int id, @RequestBody PaymentStatusUpdateDto paymentStatusUpdateDto) {
        return orderService.changePaymentStatus(id,paymentStatusUpdateDto.getPaymentStatus());
    }

    @PatchMapping("/{id}/status")
    public OrderResponseDto updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusUpdateDto orderStatusUpdateDto) {
        return orderService.changeOrderStatus(id,orderStatusUpdateDto.getOrderStatus());
    }
}
