package huylv.com.web.serviceimpl;

import huylv.com.web.controller.ProductController;
import huylv.com.web.dto.OrderDTO;
import huylv.com.web.entity.Order;
import huylv.com.web.repository.OrderRepositoty;
import huylv.com.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepositoty orderRepo;

    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order addOrder(Order newOrder) {
        Order order = new Order();
        order.setId(newOrder.getId());
        order.setOrderDate(new Date());
        order.setComment(newOrder.getComment());
        order.setOrder_user(newOrder.getOrder_user());
        order.setStatus(newOrder.getStatus());
        order.setProductList(newOrder.getProductList());
        order.setOrderPayment(newOrder.getOrderPayment());
        return orderRepo.save(order);
    }


}
