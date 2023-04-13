package huylv.com.web.service;

import huylv.com.web.entity.Order;
import huylv.com.web.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {

    Order addOrder(Order newOrder);

    List<Order> OrderByUserName(String UserName);

    List<Order> AllOrder();

    List<Order> OrderByDate(Date OrderDate);

    List<Order> FindAllByProductName(String productName);
}
