package huylv.com.web.service;

import huylv.com.web.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User creatUser(User newUser);
}
