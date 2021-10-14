package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.entity.UserGroupUser;
import by.malinka.employeeservice.persistence.UserGroupUserRepository;
import by.malinka.employeeservice.service.UserGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserGroupUserImpl implements UserGroupUserService {
    private final UserGroupUserRepository userGroupUserRepository;

    @Autowired
    public UserGroupUserImpl(UserGroupUserRepository userGroupUserRepository) {
        this.userGroupUserRepository = userGroupUserRepository;
    }

    @Override
    public UserGroupUser addUserGroupUser(UserGroupUser userGroupUser) {
        return userGroupUserRepository.save(userGroupUser);
    }

    @Override
    public void delete(int id) {
        var optionalUserGroupUser = userGroupUserRepository.findById(id);
        optionalUserGroupUser.ifPresent(userGroupUserRepository::delete);
    }

    @Override
    public UserGroupUser getByUser(User user) {
        return userGroupUserRepository.findByUserId(user);
    }

    @Override
    public Page<UserGroupUser> getAllUsersInGroup(User user, Pageable pageable) {
        return userGroupUserRepository.findAllByUserGroupId(user ,pageable);
    }

    @Override
    public UserGroupUser editUserGroup(UserGroupUser userGroupUser) {
        return userGroupUserRepository.save(userGroupUser);
    }

    @Override
    public Page<UserGroupUser> findPaginated(Pageable pageable) {

        return userGroupUserRepository.findAll(pageable);
    }
}
