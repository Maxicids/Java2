package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
}
