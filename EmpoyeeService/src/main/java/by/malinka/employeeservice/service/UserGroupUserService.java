package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.entity.UserGroup;
import by.malinka.employeeservice.entity.UserGroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupUserService {
    UserGroupUser addUserGroupUser(UserGroupUser userGroupUser);
    void delete(int id);
    void removeUser(int groupId, int userId);
    UserGroupUser getByUser(User user);
    Page<UserGroupUser> getAllUsersInGroup(UserGroup userGroup, Pageable pageable);
    UserGroupUser editUserGroup(UserGroupUser userGroupUser);
    Page<UserGroupUser> findPaginated(Pageable pageable);
}
