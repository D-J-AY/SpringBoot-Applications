package Spring.GopalG.entity;


import Spring.GopalG.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "phone_verified")
    private boolean phoneVerified;

    @Column(name = "is_active")
    private boolean activeStatus;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
