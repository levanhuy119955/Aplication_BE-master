package huylv.com.web.controller;

import huylv.com.web.entity.Product;
import huylv.com.web.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    private static Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/creatProduct")
    public ResponseEntity<Product> creatProduct(@Validated @RequestBody Product product) {
        log.info("Gọi hàm thêm mới sản phẩm");
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product1);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateproduct(@PathVariable int id, @RequestBody Product productNew) {
        log.info("Thay đổi sản phẩm theo ID");
        Product product2 = productService.updateProduct(id, productNew);
        return ResponseEntity.ok(product2);
    }

    @GetMapping("/allProduct")
    public Page<Product> getProductPage() {
        log.info("Lấy danh sách sản phẩm");
        Page<Product> product3 = productService.getProducts();
        return product3;
    }

    @GetMapping("/getPriceBettween")
    public List<Product> getProductByPrice(@RequestParam(name = "ao_xin") String name) {
        log.info("Lấy ra sản phẩm áo xịn có giá trong khoảng 400.0 - 700.0");
        List<Product> product4 = productService.getPrductByPriceBettween(name);
        return product4;
    }

    @GetMapping("/getProductByPrice")
    public List<Product> getProductPrice(@RequestParam(name = "price") Double price) {
        log.info("Lấy sản phẩm theo giá");
        List<Product> product5 = productService.getProductByPrice(price);
        return product5;
    }

    @GetMapping("/getProductName")
    public List<Product> getProductName(@RequestParam(name = "productName") String productName) {
        log.info("Lấy ra danh sách sản phẩm theo tên");
        List<Product> products6 = productService.getProductNameContaining(productName);
        return products6;
    }

    @GetMapping("/countProductName")
    public int countProductByProductName(@RequestParam(name = "productName") String productName) {
        log.info("Đếm số lượng sản phẩm theo tên");
        int count = productService.coutProductname(productName);
        return count;
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductId(@PathVariable Integer id) {
        log.info("Xóa sản phẩm theo ID");
        this.productService.deleteProductById(id);
        return "Thành công";
    }

    @DeleteMapping("/deleteAllProduct")
    public String deleteAllProduct() {
        log.info("Xóa tất cả sản phẩm");
        this.productService.deleteAllProduct();
        return "Thành công";
    }
}
