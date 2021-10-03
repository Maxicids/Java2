package by.malinka.empoyeeservice.service;

import by.malinka.empoyeeservice.entity.Role;
import java.util.List;

public interface RoleService {
    Role addRole(Role role);
    void delete(int id);
    Role getByRoleName(String name);
    Role editRole(Role role);
    List<Role> getAll();
}
