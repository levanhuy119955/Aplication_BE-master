package huylv.com.web.serviceimpl;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import huylv.com.web.aplicationGlobal.UserGlobal;
import huylv.com.web.dto.UserDTO;
import huylv.com.web.entity.User;
import huylv.com.web.repository.UserRepository;
import huylv.com.web.service.UserService;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.invoke.StringConcatException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private static final String AVATAT_DIR = "C:\\Users\\HP\\Desktop\\Avatar";

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User creatUser(User user) {
        log.info("Kiểm tra tên user");
        User checkName = userRepository.findByUserName(user.getUserName());
        if (checkName == null) {
            log.info("Tạo mới user nếu chưa có user này");
            String pass = user.getPassWord();
            String encode = Base64.getEncoder().encodeToString(pass.getBytes(StandardCharsets.UTF_8));
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setPassWord(encode);
            newUser.setNumber(user.getNumber());
            newUser.setEmail(user.getEmail());
            newUser.setAvatar(user.getAvatar());
            newUser.setRoleList(user.getRoleList());
            userRepository.save(newUser);
            return newUser;
        } else {
            log.error("Đã tồn tại user này");
            throw new UserGlobal("Đã có User " + user.getUserName() + ", bạn thử lại nhé!");
        }
    }

    @Override
    public User updateUser(Integer id, UserDTO updateUser) {
        log.info("Kiểm tra có tồn tại user theo ID");
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserGlobal("Không có User " + id + ", bạn thử lại nhé!");
        }
        log.info("Kiểm tra có tồn tại tên user này chưa");
        User checkName = userRepository.findByUserName(updateUser.getUserName());
        if (checkName != null && !checkName.getId().equals(id)) {
            throw new UserGlobal("Đã tồn tại user " + checkName.getUserName() + " bạn nhập lại nhé!");
        }
        try {
            user.setUserName(updateUser.getUserName());
            user.setNumber(updateUser.getNumber());
            user.setEmail(updateUser.getEmail());
            return userRepository.save(user);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Có lỗi xảy ra!");
        }
    }

    @Override
    public User updatePassword(String userName, String oldPassword, String newPassword, String confirmPassword) throws StringConcatException {
        log.info("Lấy ra tên user");
        User getUserName = userRepository.findByUserName(userName);
        // Kiểm tra user có tồn tại
        if (userName == null) {
            throw new StringConcatException("Không có user " + userName);
            // Kiểm tra mật khẩu mới có đúng với mật khẩu hiện tại
        } else if (!newPassword.matches(getUserName.getPassWord())) {
            throw new StringConcatException("Mật khẩu cũ không chính xác!");
            // Xác nhận mật khẩu mới
        } else if (!newPassword.equals(confirmPassword)) {
            throw new StringConcatException("Mật khẩu mới không trùng nhau!");
        } else {
            try {
                getUserName.setPassWord(newPassword);
                return userRepository.save(getUserName);
            } catch (Exception ex) {
                throw new StringConcatException("Có lỗi xảy ra!");
            }
        }
    }

    @Override
    public void updateAvatar(Integer id, MultipartFile file) throws IOException {
        User userID = userRepository.findById(id).orElseThrow(() -> new UserGlobal("Không có user ID " + id + ", bạn thử lại nhé!"));
        log.info("Loại bỏ ký tự không hợp lệ trong tên file");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType(); // Lấy ra kiểu của file
        if (!fileType.startsWith("image/")) {
            throw new InvalidClassException("File không đúng định dạng!");
        } else {
            log.info("");
            Path path = Paths.get(AVATAT_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            userID.setAvatar(path.toString());
            userRepository.save(userID);
        }
    }

    @Override
    public String forgotPassword(String userName, String email, String number, String newPass, String conFirmPass) {
        log.info("Kiểm tra tên user");
        User userCheckName = userRepository.findByUserName(userName);
        if (userCheckName.getUserName() == null) {
            throw new UserGlobal("Không tồn tại user " + userName + ", bạn thử lại nhé!");
        } else if (!userCheckName.getEmail().matches(email)) {
            throw new UserGlobal("Email không chính xác!");
        } else if (!userCheckName.getNumber().equals(number)) {
            throw new UserGlobal("Số điện thoại không chính xác!");
        } else {
            userCheckName.setPassWord(newPass);
            if (!newPass.matches(conFirmPass)) {
                throw new UserGlobal("Mật khẩu mới phải trùng khớp!");
            } else {
                userRepository.save(userCheckName);
                return "Lấy lại mật khẩu thành công!";
            }
        }
    }


}
