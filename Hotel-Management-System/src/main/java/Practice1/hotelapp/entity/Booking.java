package Practice1.hotelapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID",nullable = false)
    private Customer customer;

    @Column(name = "RoomID")
    private int roomId;

    @Column(name = "StaffID")
    private int staffId;

    @Column(name = "CheckInDate")
    private LocalDate checkInDate;

    @Column(name = "CheckOutDate")
    private LocalDate checkOutDate;

    @Column(name = "TotalAmount",precision = 10,scale = 2)
    private BigDecimal totalAmount;
}
