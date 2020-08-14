package by.edabudet.authentication.repository;
import by.edabudet.authentication.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findUserByActiveFalse();
    List<User> findUserByActiveTrue();
    User findUserByUserName(String userName);
    void deleteById(Long id);
}
