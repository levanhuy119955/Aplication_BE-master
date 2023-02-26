package huylv.com.web.controller;

import huylv.com.web.entity.Product;
import huylv.com.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.StringConcatException;
import java.util.List;

@RestController
@RequestMapping("/v1/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    // Thêm mới sản phẩm
    @PostMapping("/creatProduct")
    public ResponseEntity<Product> creatProduct(@Validated @RequestBody Product product) {
        Product product1 = productService.createProduct(product);
        return ResponseEntity.ok(product1);
    }
    // Update sản phẩm theo id
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateproduct( @PathVariable int id,@RequestBody Product productNew ){
        Product product2 = productService.updateProduct(id, productNew);
        return ResponseEntity.ok(product2);
    }
    // Lấy danh sách sản phẩm
    @GetMapping("/allProduct")
    public Page<Product> getProductPage(){
        Page<Product> product3 = productService.getProducts();
        return product3;
    }
    // Lấy ra sản phẩm áo xịn có giá trong khoảng 400.0 - 700.0
    @GetMapping("/getPriceBettween")
    public List<Product> getProductByPrice(@RequestParam (name = "ao_xin") String name) {
        List<Product> product4 = productService.getPrductByPriceBettween(name);
        return product4;
    }
    // Lấy ra sản phẩm theo giá
    @GetMapping("/getProductByPrice")
    public List<Product> getProductPrice(@RequestParam (name = "price") Double price) {
        List<Product> product5 = productService.getProductByPrice(price);
        return product5;
    }
    // Lấy ra danh sách sản phẩm theo tên
    @GetMapping("/getProductName")
    public List<Product> getProductName(@RequestParam (name = "productName") String productName){
        List<Product> products6 = productService.getProductNameContaining(productName);
        return products6;
    }
    // Đếm số lượng sản phẩm theo tên
    @GetMapping("/countProductName")
    public int countProductByProductName(@RequestParam (name = "productName") String productName){
        int count = productService.coutProductname(productName);
        return count;
    }
    // Xóa sản phẩm theo ID
    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductId(@PathVariable Integer id){
        this.productService.deleteProductById(id);
        return "Thành công";
    }
    // Xóa tất cả sản phẩm
    @DeleteMapping("/deleteAllProduct")
    public String deleteAllProduct(){
        this.productService.deleteAllProduct();
        return "Thành công";
    }
}
