package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}
