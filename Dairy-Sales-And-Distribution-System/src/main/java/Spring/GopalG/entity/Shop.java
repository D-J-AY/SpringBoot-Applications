package Spring.GopalG.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "phone",unique = true,nullable = false)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "is_active")
    private Boolean activeStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> order = new ArrayList<>();
}
