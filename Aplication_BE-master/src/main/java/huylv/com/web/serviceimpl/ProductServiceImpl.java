package huylv.com.web.serviceimpl;

import huylv.com.web.aplicationGlobal.ProductGlobal;
import huylv.com.web.entity.Product;
import huylv.com.web.repository.ProductRepository;
import huylv.com.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidClassException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final String PRODUCT_DIR = "C:\\Users\\HP\\Desktop\\Aplication_BE-master\\Aplication_BE-master\\src\\main\\java\\huylv\\com\\web\\File";

    @Override
    public Product createProduct(Product productNew) {
        Product product = productRepository.save(productNew);
        return productNew;
    }

    @Override
    public void uploadFileProduct(Integer id, MultipartFile file) throws IOException {
        Product checkID = productRepository.findById(id).orElseThrow(() -> new ProductGlobal("Không có sản phẩm ID " + id + ", bạn thử lại nhé!"));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // Lấy tên file gôc của đối tượng MultipathFile và xóa bỏ các ký tự không hợp lệ
        String fileType = file.getContentType(); // Lấy ra kiểu dữ liệu của file
        if (!fileType.startsWith("image/")) {
            // File không bắt đầu bằng chuỗi image
            throw new InvalidClassException("File không đúng định dạng");
        }
        // Tạo đường dẫn lưu trữ file trong folder chứa ảnh sản phẩm
        Path path = Paths.get(PRODUCT_DIR + fileName);
        // sao chép nội dung của file được upload vào đường dẫn đã được xác định, cho phép thay thế file nếu tập tin đã tồn tại
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        // Thiết lập đường dẫn của file vào đối tượng Product và lưu vào cơ sở dữ liệu
        checkID.setAvatar(path.toString());
        productRepository.save(checkID);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product checkID = productRepository.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào có " + id + " này, bạn thử lại!"));
        Product updatePro = new Product();
        updatePro.setId(product.getId());
        updatePro.setProductName(product.getProductName());
        updatePro.setDescription(product.getDescription());
        updatePro.setPrice(product.getPrice());
        updatePro.setAvatar(product.getAvatar());
        updatePro.setCategory_ID(product.getCategory_ID());
        productRepository.save(updatePro);
        return updatePro;
    }

    @Override
    public Page<Product> getProducts() {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    @Override
    public Product getProductID(Integer id) {
        Product getProduct = productRepository.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào ID " + id + ", Bạn thử lại nhé!"));
        return getProduct;
    }

    @Override
    public List<Product> getPrductByPriceBettween(String nameProduct) {
        List<Product> productList = productRepository.findAllByPriceBetween(400.0, 700.0);
        return productList;
    }

    @Override
    public List<Product> getProductByPrice(Double price) {
        List<Product> checkPrice = productRepository.findByPrice(price);
        if (checkPrice.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm nào có giá " + price + ", Bạn thử lại!");
        }
        List<Product> productList = productRepository.findProductByPrice(price);
        return productList;
    }

    @Override
    public void deleteProductById(Integer id) {
        Product checkId = productRepository.findById(id).orElseThrow(
                () -> new ProductGlobal("Không có sản phẩm nào có ID " + id + ", ban thử lại!"));
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductNameContaining(String name) {
        List<Product> checkName = productRepository.findAllByProductName(name);
        if (checkName == null || checkName.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm " + name + ", bạn thử lại nhé");
        }
        return productRepository.findAllByProductName(name);
    }

    @Override
    public void deleteAllProduct() {
        this.productRepository.deleteAll();
    }

    @Override
    public int coutProductname(String nameProduct) {
        List<Product> checkName = productRepository.findAllByProductName(nameProduct);
        if (checkName == null || checkName.isEmpty()) {
            throw new ProductGlobal("Không có sản phẩm " + nameProduct + ", bạn thử lại nhé");
        }
        return productRepository.countAllByProductName(nameProduct);
    }


}
