package huylv.com.web.dto;

import huylv.com.web.entity.OrderPayment;
import huylv.com.web.entity.Product;
import huylv.com.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Date orderDate;
    private String comment;
    private User order_user;
    private String status;
    private List<Product> productList;
    private List<OrderPayment> orderPayment;
}
