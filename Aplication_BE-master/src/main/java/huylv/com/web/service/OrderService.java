package huylv.com.web.service;

import huylv.com.web.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Order addOrder(Order newOrder);

}
