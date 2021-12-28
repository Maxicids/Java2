package by.malinka.service.impl;

import by.malinka.domain.Book;
import by.malinka.domain.Role;
import by.malinka.domain.User;
import by.malinka.repository.BookRepository;
import by.malinka.repository.UserRepository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        user = new User(1L, "test1", "test1@test.com", "0", "pass", null);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void findAll() {
        when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));
        Assertions.assertEquals(Collections.singletonList(user), userService.findAll());
    }

    @Test
    void findById() {
        when(userRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(user));
        Assertions.assertEquals(Optional.of(user), userService.findById(any(Long.class)));
    }

    @Test
    void saveOrUpdate() {
        when(userRepository.save(user))
                .thenReturn(user);
        Assertions.assertEquals(user, userService.saveOrUpdate(user));
    }

    @Test
    void deleteById() throws JSONException {
        doNothing().when(userRepository).deleteById(any(Long.class));
        Assertions.assertEquals(new JSONObject().put("message", "User deleted successfully").toString(), userService.deleteById(any(Long.class)));
    }

    @Test
    void findByEmail() {
    }
}