package huylv.com.web.serviceimpl;

import huylv.com.web.entity.Order;
import huylv.com.web.entity.Product;
import huylv.com.web.entity.User;
import huylv.com.web.repository.OrderRepositoty;
import huylv.com.web.repository.ProductRepository;
import huylv.com.web.repository.UserRepository;
import huylv.com.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepositoty orderRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

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

    @Override
    public List<Order> OrderByUserName(String UserName) {
        User checkName = userRepo.findByUserName(UserName);
        if (checkName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có đơn hàng nào của " + UserName);
        }
        return orderRepo.findOrderByOrder_user(UserName);
    }

    @Override
    public List<Order> AllOrder() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> OrderByDate(Date orderDate) {
        List<Order> orderList = orderRepo.findOrderByOrderDate(orderDate);
        if (orderList == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có đơn hàng nào có date là " + orderDate);
        }
        return orderList;
    }

    @Override
    public List<Order> FindAllByProductName(String productName) {
        log.info("Kiểm tra danh sách sản phẩm");
        List<Product> productList = productRepo.findAllByProductName(productName);
        if (productList.isEmpty()) {
            log.debug("Danh sách sản phẩm rỗng");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có sản tên " + productName);
        }
        log.info("Kiểm tra danh sách đơn hàng");
        List<Order> orderList = orderRepo.findAllByProductName(productName);
        if (orderList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không có đơn hàng nào có sản phẩm " + productName);
        }
        return orderList;
    }


}
