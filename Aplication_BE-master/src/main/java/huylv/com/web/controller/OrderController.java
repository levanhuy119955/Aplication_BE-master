package huylv.com.web.controller;

import huylv.com.web.dto.OrderDTO;
import huylv.com.web.entity.Order;
import huylv.com.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    private static Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrderProduct(@RequestBody Order newOrder) {
        Order order = orderService.addOrder(newOrder);
        try {
            return ResponseEntity.ok(order);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra, lỗi đã được thông báo cho quản trị!");
        }
    }
}
