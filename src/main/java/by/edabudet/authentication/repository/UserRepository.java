package by.edabudet.authentication.repository;
import by.edabudet.authentication.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUserName(String userName);
    User deleteUserByUserName(String userName);
}
