package huylv.com.web.repository;

import huylv.com.web.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepsitory extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductName(String productName);

    List<Product> findAllByPriceBetween(Double start, Double end);

    List<Product> findProductByPrice(Double price);

    List<Product> findByPrice(Double checkPrice);

    List<Product> findAllByProductNameContains(String name);

    List<Product> findAllByProductNameEndingWith(String productName);

    int countAllByProductName(String productName);
}
