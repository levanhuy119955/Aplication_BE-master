package huylv.com.web.controller;

import huylv.com.web.entity.User;
import huylv.com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    @Autowired
    UserService userService;

    // Thêm mới User
    @PostMapping("/newUser")
    public ResponseEntity<User> creatNewUser( @RequestBody User user){
        User user1 = userService.creatUser(user);
        return ResponseEntity.ok(user1);
    }
}
