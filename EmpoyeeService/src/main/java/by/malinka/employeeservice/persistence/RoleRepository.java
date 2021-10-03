package by.malinka.empoyeeservice.repository;

import by.malinka.empoyeeservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.roleName = :name")
    Role findByName(@Param("name") String name);
}
