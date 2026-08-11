package Spring.GopalG.service;

import Spring.GopalG.dto.order.*;
import Spring.GopalG.entity.Order;
import Spring.GopalG.entity.OrderItems;
import Spring.GopalG.entity.ProductVariant;
import Spring.GopalG.entity.Shop;
import Spring.GopalG.enums.OrderStatus;
import Spring.GopalG.enums.PaymentStatus;
import Spring.GopalG.exception.BadRequestException;
import Spring.GopalG.exception.ResourceNotFoundException;
import Spring.GopalG.repository.OrderItemRepository;
import Spring.GopalG.repository.OrderRepository;
import Spring.GopalG.repository.ProductVariantRepository;
import Spring.GopalG.repository.ShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ShopRepository shopRepository;
    private ProductVariantRepository  productVariantRepository;
    public OrderService(OrderRepository orderRepository, ShopRepository shopRepository,
                        ProductVariantRepository productVariantRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.shopRepository = shopRepository;
        this.productVariantRepository = productVariantRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        int shopId = orderRequestDto.getShopId();
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()->new ResourceNotFoundException("Shop not found with id " + shopId));

        Order order = new Order();
        order.setShop(shop);
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        if(orderRequestDto.getDeliveryDate().isBefore(LocalDate.now())){
            throw new BadRequestException(
                    "Delivery date cannot be in the past"
            );
        }
        order.setDeliveryDate(orderRequestDto.getDeliveryDate());
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setTotalAmount(BigDecimal.ZERO);

        Order savedOrder = orderRepository.save(order);

        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequestDto orderItemRequestDto : orderRequestDto.getOrderItems()) {
            if (orderItemRequestDto.getQuantity() <= 0) {
                throw new BadRequestException("Quantity must be greater than 0");
            }

            ProductVariant productVariant = productVariantRepository.findById(orderItemRequestDto.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Product Variant not found with id " + orderItemRequestDto
                            .getVariantId()));

            if (!productVariant.isActiveStatus()) {
                throw new BadRequestException("Product Variant is inactive");
            }

            OrderItems orderItem = new OrderItems();

            orderItem.setOrder(savedOrder);
            orderItem.setProductVariant(productVariant);
            orderItem.setQuantity(orderItemRequestDto.getQuantity());
            orderItem.setPricePerUnit(productVariant.getPricePerPack());

            BigDecimal subTotal = productVariant.getPricePerPack()
                    .multiply(BigDecimal.valueOf(orderItemRequestDto.getQuantity()));

            orderItem.setSubtotal(subTotal);


            totalAmount = totalAmount.add(subTotal);

            savedOrder.getOrderItems().add(orderItem);
            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);
        savedOrder.setTotalAmount(totalAmount);

        Order updatedOrder = orderRepository.save(savedOrder);
        return toResponse(updatedOrder);
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<OrderResponseDto> getOrdersByShopId(int shopId) {
        return orderRepository.findByShop_Id(shopId).stream().map(this::toResponse).toList();
    }

    public OrderResponseDto changeOrderStatus(int orderId,OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));


        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return toResponse(order);
    }

    public OrderResponseDto changePaymentStatus(int orderId,PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        order.setPaymentStatus(paymentStatus);
        orderRepository.save(order);
        return toResponse(order);
    }

    private OrderResponseDto toResponse(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderNumber(order.getOrderNumber());
        orderResponseDto.setShopId(order.getShop().getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setDeliveryDate(order.getDeliveryDate());
        orderResponseDto.setTotalAmount(order.getTotalAmount());
        orderResponseDto.setPaymentStatus(order.getPaymentStatus());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreated());
        orderResponseDto.setUpdatedAt(order.getUpdated());

        List<OrderItemResponseDto> orderItemResponseDtos = new ArrayList<>();

        for (OrderItems orderItem : order.getOrderItems()) {
            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
            orderItemResponseDto.setOrderItemId(orderItem.getOrderItemId());
            orderItemResponseDto.setVariantId(orderItem.getProductVariant().getVariantId());
            orderItemResponseDto.setQuantity(orderItem.getQuantity());
            orderItemResponseDto.setPricePerUnit(orderItem.getPricePerUnit());
            orderItemResponseDto.setSubTotal(orderItem.getSubtotal());

            orderItemResponseDtos.add(orderItemResponseDto);

        }

        orderResponseDto.setOrderItems(orderItemResponseDtos);

        return orderResponseDto;
    }
}
