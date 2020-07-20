package by.edabudet.authentication.repository;


import by.edabudet.authentication.bean.Role;
import by.edabudet.authentication.bean.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(UserRoles role);
}

