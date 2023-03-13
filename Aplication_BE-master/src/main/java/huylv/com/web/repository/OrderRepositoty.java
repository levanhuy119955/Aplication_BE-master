package huylv.com.web.repository;

import huylv.com.web.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositoty extends JpaRepository<Order, Integer> {

}
