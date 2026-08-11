package Spring.GopalG.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "unit", nullable = false)
    private String productUnit;

    @Column(name = "active_status",nullable = false)
    private boolean activeStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();
}
