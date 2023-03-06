package huylv.com.web.aplicationGlobal;

import huylv.com.web.aplicationGlobal.entity.ProductEntity;
import huylv.com.web.aplicationGlobal.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionProcess {

    @ExceptionHandler(value = ProductGlobal.class)
    public ResponseEntity<Object> productEx(ProductGlobal productGlobal){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode("001");
        productEntity.setDescription(productGlobal.getMessage());
        productEntity.setTimetemp(new Date());
        return new ResponseEntity<>(productEntity, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserGlobal.class)
    public ResponseEntity<Object> userEx(UserGlobal userGlobal){
        UserEntity user = new UserEntity();
        user.setCode("001");
        user.setDescription(userGlobal.getMessage());
        user.setTimetemp(new Date());
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler (value = ExceptionGlobal.class)
//    public Map<Object,String> mapExxception(ExceptionGlobal excepGlobal){
//        CategoryExceptionEntity categoryEx = new CategoryExceptionEntity();
//        Map<Object,String> maps = new HashMap<>();
//
//        maps.put(categoryEx.getCode(),"100");
//        maps.put(categoryEx.getMessage(),"Category name độ dài phải lớn hơn 5");
//        maps.put(categoryEx.getTimestamp().toString(),"");
//        return maps;
//    }
}
