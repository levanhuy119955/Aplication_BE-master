package huylv.com.web.service;

import huylv.com.web.dto.UserDTO;
import huylv.com.web.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.IOException;
import java.lang.invoke.StringConcatException;

@Service
public interface UserService {
    User creatUser(User newUser);

    User updateUser(Integer id, UserDTO updateUser);

    User updatePassword(String userName, String oldPassword, String newPassword, String confirmPassword) throws StringConcatException;

    void updateAvatar(Integer id, MultipartFile file) throws IOException;

    String forgotPassword(String userName, String email, String number, String newPass, String conFirmPass);

}
