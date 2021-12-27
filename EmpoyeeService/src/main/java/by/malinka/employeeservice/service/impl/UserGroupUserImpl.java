package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.entity.UserGroup;
import by.malinka.employeeservice.entity.UserGroupUser;
import by.malinka.employeeservice.persistence.UserGroupRepository;
import by.malinka.employeeservice.persistence.UserGroupUserRepository;
import by.malinka.employeeservice.persistence.UserRepository;
import by.malinka.employeeservice.service.UserGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserGroupUserImpl implements UserGroupUserService {
    private final UserGroupUserRepository userGroupUserRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserGroupUserImpl(UserGroupUserRepository userGroupUserRepository,
                             UserGroupRepository userGroupRepository,
                             UserRepository userRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.userGroupUserRepository = userGroupUserRepository;
    }

    @Override
    public UserGroupUser addUserGroupUser(UserGroupUser userGroupUser) {
        return userGroupUserRepository.save(userGroupUser);
    }

    @Override
    public void delete(int id) {
        Optional<UserGroupUser> optionalUserGroupUser = userGroupUserRepository.findById(id);
        optionalUserGroupUser.ifPresent(userGroupUserRepository::delete);
    }

    @Override
    public void removeUser(int groupId, int userId) {
        userGroupUserRepository.deleteByUserGroupIdAndUserId(
                userGroupRepository.findById(groupId).get(), userRepository.findById(userId).get()
        );
    }

    @Override
    public UserGroupUser getByUser(User user) {
        return userGroupUserRepository.findByUserId(user);
    }

    @Override
    public Page<UserGroupUser> getAllUsersInGroup(UserGroup userGroup, Pageable pageable) {
        return userGroupUserRepository.findAllByUserGroupId(userGroup ,pageable);
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
