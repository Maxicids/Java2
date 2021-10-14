package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.Role;
import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin("http")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser() {
        var login = request.getParameter("content");
        //userService.registerUser(new User(1, new Role(0, "ADMIN"), "max@gmail.com", "b", "v"));
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping
    public Page<User> findPaginated(Pageable pageable) {
        return userService.findPaginated(pageable);
    }
}
