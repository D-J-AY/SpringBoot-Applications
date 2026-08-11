package Spring.GopalG.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int variantId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name = "pack_size",nullable = false)
    private BigDecimal packSize;

    @Column(name = "unit", nullable = false)
    private String variantUnit;

    @Column(name = "price_per_pack",precision = 10,scale = 2, nullable = false)
    private BigDecimal pricePerPack;

    @Column(name = "active_status",nullable = false)
    private boolean activeStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated;

    @OneToMany(mappedBy = "productVariant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();
}
