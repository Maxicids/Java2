package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.Role;
import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    void delete(int id);
    Role getByRoleName(String name);
    Role editRole(Role role);
    List<Role> getAll();
}
