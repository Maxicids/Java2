package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.UserRepository;
import by.malinka.employeeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public Page<User> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
