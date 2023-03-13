package huylv.com.web.service;

import huylv.com.web.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product createProduct(Product productNew);

    void uploadFileProduct(Integer id, MultipartFile file) throws IOException;

    Product updateProduct(Integer id, Product product);

    Page<Product> getProducts();

    Product getProductID(Integer id);

    List<Product> getPrductByPriceBettween(String nameProduct);

    List<Product> getProductByPrice(Double price);

    void deleteProductById(Integer id);

    List<Product> getProductNameContaining(String productName);

    void deleteAllProduct();

    int coutProductname(String nameProduct);

}
