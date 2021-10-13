package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    void delete(int id);
    User getByEmail(String email);
    User editUser(User user);
    Page<User> findPaginated(Pageable pageable);
}
