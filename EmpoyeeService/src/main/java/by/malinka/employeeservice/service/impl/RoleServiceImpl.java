package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.Role;
import by.malinka.employeeservice.persistence.RoleRepository;
import by.malinka.employeeservice.service.RoleService;
import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role) {
        if (roleRepository.findById(role.getId()).isPresent()) {
            throw new UserAlreadyExistsException("User with email have " + role.getRoleName() + " already existed"); // TODO: Exception
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(int id) {
        var optionalUser = roleRepository.findById(id);
        optionalUser.ifPresent(roleRepository::delete);
    }

    @Override
    public Role getByRoleName(String name) {
        Optional<Role> optionalRole;
        optionalRole = roleRepository.findByRoleName(name);
        return optionalRole.orElse(null);
    }

    @Override
    public Role editRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Page<Role> findPaginated(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
