package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.entity.UserGroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupUserRepository extends PagingAndSortingRepository<UserGroupUser, Integer> {
    Page<UserGroupUser> findAllByUserGroupId(User user, Pageable pageable);
    UserGroupUser findByUserId(User user);
}
