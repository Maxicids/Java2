package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.entity.UserGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, Integer> {
    Optional<UserGroup> findByGroupName(String name);
}
