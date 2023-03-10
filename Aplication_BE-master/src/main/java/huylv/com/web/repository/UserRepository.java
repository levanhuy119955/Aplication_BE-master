package huylv.com.web.repository;

import huylv.com.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findByPassWord(String password);


}
