package huylv.com.web.serviceimpl;

import huylv.com.web.aplicationGlobal.ProductGlobal;
import huylv.com.web.entity.Product;
import huylv.com.web.repository.ProductRepsitory;
import huylv.com.web.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepsitory productRepsitory;


    @Override
    public Product createProduct(Product productNew) {
        Product product = productRepsitory.save(productNew);
        return productNew;
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product checkID = productRepsitory.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào có " + id + " này, bạn thử lại!"));
        Product updatePro = new Product();
        updatePro.setId(product.getId());
        updatePro.setProductName(product.getProductName());
        updatePro.setDescription(product.getDescription());
        updatePro.setPrice(product.getPrice());
        updatePro.setAvatar(product.getAvatar());
        updatePro.setCategoryID(product.getCategoryID());
        productRepsitory.save(updatePro);
        return updatePro;
    }

    @Override
    public Page<Product> getProducts() {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Product> products = productRepsitory.findAll(pageable);
        return products;
    }

    @Override
    public Product getProductID(Integer id) {
        Product getProduct = productRepsitory.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào ID " + id + ", Bạn thử lại nhé!"));
        return getProduct;
    }

    @Override
    public List<Product> getPrductByPriceBettween(String nameProduct) {
        List<Product> productList = productRepsitory.findAllByPriceBetween(400.0, 700.0);
        return productList;
    }

    @Override
    public List<Product> getProductByPrice(Double price) {
        List<Product> checkPrice = productRepsitory.findByPrice(price);
        if (checkPrice.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm nào có giá " + price + ", Bạn thử lại!");
        }
        List<Product> productList = productRepsitory.findProductByPrice(price);
        return productList;
    }

    @Override
    public void deleteProductById(Integer id) {
        Product checkId = productRepsitory.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào có ID " + id + ", ban thử lại!"));
        this.productRepsitory.deleteById(id);
    }

    @Override
    public List<Product> getProductNameContaining(String name) {
        List<Product> checkName = productRepsitory.findAllByProductName(name);
        if (checkName == null || checkName.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm " + name + ", bạn thử lại nhé");
        }
        return productRepsitory.findAllByProductName(name);
    }

    @Override
    public void deleteAllProduct() {
        this.productRepsitory.deleteAll();
    }

    @Override
    public int coutProductname(String nameProduct) {
        List<Product> checkName = productRepsitory.findAllByProductName(nameProduct);
        if (checkName == null || checkName.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm " + nameProduct + ", bạn thử lại nhé");
        }
        return productRepsitory.countAllByProductName(nameProduct);
    }


}
