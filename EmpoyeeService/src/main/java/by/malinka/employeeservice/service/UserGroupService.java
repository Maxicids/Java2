package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.UserGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserGroupService {
    UserGroup addGroup(UserGroup userGroup);
    void delete(int id);
    UserGroup getByGroupName(String groupName);
    UserGroup editUserGroup(UserGroup userGroup);
    Page<UserGroup> findPaginated(Pageable pageable);
}
