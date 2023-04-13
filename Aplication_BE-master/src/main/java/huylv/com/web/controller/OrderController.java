package huylv.com.web.controller;

import huylv.com.web.dto.OrderDTO;
import huylv.com.web.entity.Order;
import huylv.com.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

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

    @GetMapping("/getOrderByUserName")
    public ResponseEntity<?> getOrderByUser(@RequestParam String userName) {
        List<Order> orderUser = orderService.OrderByUserName(userName);
        try {
            return new ResponseEntity<>(orderUser, HttpStatus.OK);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra, lỗi đã được thông báo cho quản trị!");
        }
    }

    @GetMapping("/getAllOrder")
    public void getAll(){
        this.orderService.AllOrder();
    }

    @GetMapping("/orderByDate")
    public ResponseEntity<?> getOrderByDate(@RequestParam Date date){
        List<Order> orderList = orderService.OrderByDate(date);
        try {
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }catch (Exception ex){
            throw new RuntimeException("Có lỗi xảy ra, lỗi đã được thông báo cho quản trị!");
        }
    }

    @GetMapping("/getOrderByProduct")
    public ResponseEntity<?> getAllOrderByProduct(@RequestParam String productName){
        List<Order> orderListProduct = orderService.FindAllByProductName(productName);
        try {
            return new ResponseEntity<>(orderListProduct, HttpStatus.OK);
        }catch (RuntimeException ex){
            throw new RuntimeException("Có lỗi xảy ra, lỗi đã được thông báo cho quản trị!");
        }
    }
}