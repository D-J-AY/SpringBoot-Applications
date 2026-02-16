package Practice1.hotelapp.repository;

import Practice1.hotelapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
