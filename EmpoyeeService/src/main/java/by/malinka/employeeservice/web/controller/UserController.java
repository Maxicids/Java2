package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.model.UserRequest;
import by.malinka.employeeservice.model.UserResponse;
import by.malinka.employeeservice.security.JwtModel;
import by.malinka.employeeservice.security.JwtSecurityService;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final JwtSecurityService securityService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, JwtSecurityService securityService) {
        this.securityService = securityService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody @Valid UserRequest userRequest,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadCredentialsException("Invalid data");
        }

        try {
            JwtModel model = securityService.autologin(request, response,
                    userRequest.getEmail(), userRequest.getPassword());
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid data");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            HttpServletRequest request, HttpServletResponse response,
            @RequestBody @Valid UserRequest userRequest,
            BindingResult bindingResult
            ) {
        log.info(userRequest.toString());
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        User user = new User(userRequest);
        user.setRoleId(roleService.getByRoleName("USER"));
        String password = user.getPassword();
        user = userService.registerUser(user.setPassword(securityService.getHashedPassword(password)));
        JwtModel model = securityService.autologin(request, response,
                user.getEmail(), password);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
        @RequestBody @Valid UserRequest userRequest,
        BindingResult bindingResult) {
        log.info(userRequest.toString());
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        User user = userService.getByEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setPassword(userRequest.getPassword());
        userService.editUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(new UserResponse(userService.getByEmail(email)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.ok(new UserResponse(userService.findById(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Page<User> findAll(Pageable pageable) {
        return userService.findPaginated(pageable);
    }
}
