package huylv.com.web.serviceimpl;

import huylv.com.web.aplicationGlobal.UserGlobal;
import huylv.com.web.entity.User;
import huylv.com.web.repository.UserRepository;
import huylv.com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User creatUser(User user) {
        // Kiểm tra tên user đã tồn tại
        Optional<User> checkName = userRepository.findByUserName(user.getUserName());
        if (checkName.isEmpty()){
            // Chuyển đổi mật khẩu sang dạng Base64
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
        }else {
            throw new UserGlobal("Đã có User " + user.getUserName() + ", bạn thử lại nhé!");
        }
    }


}
