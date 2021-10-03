package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.Role;
import by.malinka.employeeservice.persistence.RoleRepository;
import by.malinka.employeeservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getByRoleName(String name) {
        return null;
    }

    @Override
    public Role editRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

}
