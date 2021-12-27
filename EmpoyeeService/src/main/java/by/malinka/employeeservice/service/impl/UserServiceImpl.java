package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.UserRepository;
import by.malinka.employeeservice.service.UserService;
import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import by.malinka.employeeservice.service.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email have " + user.getEmail() + " already existed");
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(userRepository::delete);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser;
        optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("User with name " + name + " not found"));
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
