package Spring.GopalG.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id",nullable = false)
    private ProductVariant productVariant;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @Column(name="price_per_unit",precision = 10,scale = 2,nullable = false)
    private BigDecimal pricePerUnit;

    @Column(name = "subtotal",precision = 10,scale = 2, nullable = false)
    private BigDecimal subtotal;
}
