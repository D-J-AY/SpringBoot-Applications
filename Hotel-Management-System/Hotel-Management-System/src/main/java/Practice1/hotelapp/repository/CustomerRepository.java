package Practice1.hotelapp.repository;

import Practice1.hotelapp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Page<Customer> findByCityIgnoreCase(String city, Pageable pageable);

}
