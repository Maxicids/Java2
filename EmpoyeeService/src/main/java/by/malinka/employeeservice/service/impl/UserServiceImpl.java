package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.UserRepository;
import by.malinka.employeeservice.service.UserService;
import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
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
        var optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(userRepository::delete);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> optionalUser;
        optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser;
        optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
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
