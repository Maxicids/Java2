package by.malinka.empoyeeservice.service;

import by.malinka.empoyeeservice.entity.User;
import java.util.List;

public interface UserService {
    User addUser(User user);
    void delete(int id);
    User getByEmail(String email);
    User editUser(User user);
    List<User> getAll();
}
