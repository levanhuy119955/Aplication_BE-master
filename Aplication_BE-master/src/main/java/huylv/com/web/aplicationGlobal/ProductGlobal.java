package huylv.com.web.aplicationGlobal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ProductGlobal extends RuntimeException{
    public ProductGlobal(String message) {
        super(message);
    }
}
