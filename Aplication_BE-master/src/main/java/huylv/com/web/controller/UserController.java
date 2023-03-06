package huylv.com.web.controller;

import huylv.com.web.dto.UserDTO;
import huylv.com.web.entity.User;
import huylv.com.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.lang.invoke.StringConcatException;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    @Autowired
    UserService userService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    // Thêm mới user
    @PostMapping("/newUser")
    public ResponseEntity<User> creatNewUser(@RequestBody User user) {
        log.info("Gọi hàm tạo mới user");
        User user1 = userService.creatUser(user);
        return ResponseEntity.ok(user1);
    }

    // Update user theo ID
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateNewUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        log.info("Gọi hàm update user (Tên, SĐT, Email) theo ID");
        User user2 = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user2);
    }

    // Cập nhật lại mật khẩu của user
    @PutMapping("/updatePassword")
    public ResponseEntity<User> updatePass(@RequestParam(name = "userName") String userName, @RequestParam(name = "oldPassword") String pass,
                                           @RequestParam(name = "newPass") String newPassword, @RequestParam(name = "confirmPass") String confirmPass) throws StringConcatException {
        var user3 = userService.updatePassword(userName, pass, newPassword, confirmPass);
        return ResponseEntity.ok(user3);
    }

    // Upload avatar cho user theo ID lưu vào thư mục trên server
    @PostMapping("/UploadAvatar/{id}")
    public ResponseEntity<String> uploadAvatar(@PathVariable Integer id, MultipartFile file) throws IOException {
        userService.updateAvatar(id, file);
        return ResponseEntity.ok().body("Upload file thành công");
    }

    // Lấy lại mật khẩu theo tên user
    @PostMapping("/fogotPassword")
    public ResponseEntity<String> fogotPass(@RequestParam("userName") String name, @RequestParam("email") String email,
                                            @RequestParam("number") String number, @RequestParam("newPass") String newPassword, @RequestParam("confirm") String confirm) throws RuntimeException {
        try {
            userService.forgotPassword(name, email, number, newPassword, confirm);
            return ResponseEntity.ok().body("lấy lại mật khẩu thành công");
        } catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra!");
        }
    }

}
