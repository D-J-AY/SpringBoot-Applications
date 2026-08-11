package Spring.GopalG.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="otp")
public class OtpVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="phone",nullable = false,unique = true,length = 15)
    private String phone;

    @Column(name = "otp",nullable = false,length = 6)
    private String otp;

    @Column(name = "verified",nullable = false)
    private boolean verified;

    @Column(name = "expires_at",nullable = false)
    private LocalDateTime expiresAt;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;
}
