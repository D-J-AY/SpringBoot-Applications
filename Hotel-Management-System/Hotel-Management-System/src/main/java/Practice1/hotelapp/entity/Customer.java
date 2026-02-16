package Practice1.hotelapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "CustomerID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "FirstName",length = 50)
    private String firstName;

    @Column(name = "LastName",length = 50)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="Phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy ="customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings;
}
