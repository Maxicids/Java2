package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User registerUser(User user);
    void delete(int id);
    User getByEmail(String email);
    User findById(int id);
    User findByName(String name);
    User editUser(User user);
    Page<User> findPaginated(Pageable pageable);
}
