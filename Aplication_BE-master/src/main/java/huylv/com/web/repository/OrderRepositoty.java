package huylv.com.web.repository;

import huylv.com.web.entity.Order;
import huylv.com.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepositoty extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.order_user.userName = ?1 ")
    List<Order> findOrderByOrder_user(String userName);

    List<Order> findOrderByOrderDate(Date orderDate);

    @Query("SELECT o FROM Order o JOIN o.productList p WHERE p.productName = :productName")
    List<Order> findAllByProductName(@Param("productName") String productName);
}
