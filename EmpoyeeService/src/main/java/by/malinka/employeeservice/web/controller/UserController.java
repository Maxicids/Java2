package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.model.UserRequest;
import by.malinka.employeeservice.service.RoleService;
import by.malinka.employeeservice.service.UserService;
import by.malinka.employeeservice.service.exception.user.UserNotFoundException;
import by.malinka.employeeservice.service.exception.user.UserValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
//@CrossOrigin("http")
@RequestMapping("/users")
public class UserController {

    static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("email") String email) {
        var user = userService.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email: " + email + " does not exist");
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid UserRequest userRequest,
            BindingResult bindingResult
            ) {
        log.info(userRequest.toString());
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        User user = new User(userRequest);
        user.setRoleId(roleService.getByRoleName("ROLE_USER"));
        user = userService.registerUser(user);
        System.out.println("tut " + user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(

    ) {
//        if (!password.equals(repeatedPassword)) {
//            throw new PasswordsDoNotMatchException("Entered passwords do not match");
//        }
//        var user = userService.findById(id);
//        if (user == null) {
//            throw new UserNotFoundException("No such user");
//        }
//        user.setEmail(email);
//        user.setName(name);
//        user.setSurname(surname);
//        user.setPassword(password);
//        userService.editUser(user);
       return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public Page<User> findPaginated(Pageable pageable) {
        return userService.findPaginated(pageable);
    }
}
