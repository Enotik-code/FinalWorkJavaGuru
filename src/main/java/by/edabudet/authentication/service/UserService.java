package by.edabudet.authentication.service;


import by.edabudet.authentication.bean.Role;
import by.edabudet.authentication.bean.User;
import by.edabudet.authentication.bean.UserRoles;
import by.edabudet.authentication.repository.RoleRepository;
import by.edabudet.authentication.repository.UserRepository;
import by.edabudet.config.DatabaseConection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public User saveUser(User user, Optional<UserRoles> userRoleOp) {
        UserRoles newUserRole = userRoleOp.orElseGet(() -> UserRoles.ROLE_GUEST);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRoleName(newUserRole);
        if (userRole == null) {
            userRole = roleRepository.save(new Role(null, newUserRole));
        }
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public void updateUserInfo(User user){
       // userRepository.save(user);
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public String generateActivationCode(){
        Random r = new Random();
        return  String.valueOf(r.nextInt((999999 - 100000) + 1) + 100000);
    }

    public void deactivateUser(Long id) throws SQLException {
        String query = " update users " +
                "set users.active = 0 " +
                "where users.id_user = " + id;
        try (PreparedStatement preparedStatement = DatabaseConection.getDbConnection().prepareStatement(query)) {
            preparedStatement.execute();
        }
    }

    public void activateUser(Long id) throws SQLException {
        String query = "update users " +
                "set users.active = 1" +
                " where users.id_user = " + id;
        try (PreparedStatement preparedStatement = DatabaseConection.getDbConnection().prepareStatement(query)) {
            preparedStatement.execute();
        }
    }
}
