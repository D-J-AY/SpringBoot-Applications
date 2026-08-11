package Spring.GopalG.repository;

import Spring.GopalG.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByShop_Id(int shopId);
}
