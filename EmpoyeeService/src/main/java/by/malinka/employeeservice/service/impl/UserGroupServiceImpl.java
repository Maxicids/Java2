package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.UserGroup;
import by.malinka.employeeservice.persistence.UserGroupRepository;
import by.malinka.employeeservice.service.UserGroupService;
import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public UserGroup addGroup(UserGroup userGroup) {
        if (userGroupRepository.findById(userGroup.getId()).isPresent()) {
            throw new UserAlreadyExistsException("User with email have " + userGroup.getGroupName() + " already existed"); // TODO: Exception
        }
        return userGroupRepository.save(userGroup);
    }

    @Override
    public void delete(int id) {
        Optional<UserGroup> optionalUserGroup = userGroupRepository.findById(id);
        optionalUserGroup.ifPresent(userGroupRepository::delete);
    }

    @Override
    public UserGroup getByGroupName(String groupName) {
        Optional<UserGroup> optionalUserGroup;
        optionalUserGroup = userGroupRepository.findByGroupName(groupName);
        return optionalUserGroup.orElse(null);
    }

    @Override
    public UserGroup getById(int id) {
        Optional<UserGroup> optionalUserGroup;
        optionalUserGroup = userGroupRepository.findById(id);
        return optionalUserGroup.orElse(null);
    }

    @Override
    public UserGroup editUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    @Override
    public Page<UserGroup> findPaginated(Pageable pageable) {
        return userGroupRepository.findAll(pageable);
    }
}
