package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.UserGroup;
import by.malinka.employeeservice.entity.UserGroupUser;
import by.malinka.employeeservice.model.UserGroupRequest;
import by.malinka.employeeservice.service.UserGroupService;
import by.malinka.employeeservice.service.UserGroupUserService;
import by.malinka.employeeservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-groups")
@CrossOrigin(origins = "http://localhost:3000")
public class UserGroupController {
    static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserGroupService userGroupService;
    private final UserService userService;
    private final UserGroupUserService userGroupUserService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService,
                               UserGroupUserService userGroupUserService,
                               UserService userService) {
        this.userGroupService = userGroupService;
        this.userService = userService;
        this.userGroupUserService = userGroupUserService;
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("id") int id) {
        return ResponseEntity.ok(userGroupService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> add(
            @RequestBody @Valid UserGroupRequest userGroupRequest
    ) {
        return ResponseEntity.ok(userGroupService.addGroup(new
                UserGroup(userGroupRequest.getGroupName())));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("id") int id) {
        userGroupService.delete(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @PostMapping("/{id}/users/{userId}")
    public ResponseEntity<?> addUser(
            @PathVariable int id,
            @PathVariable int userId
    ) {
        return ResponseEntity.ok(userGroupUserService.addUserGroupUser(new UserGroupUser(
                userService.findById(userId), userGroupService.getById(id)
        )));
    }

    @DeleteMapping("/{id}/users/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable int id,
            @PathVariable int userId
    ) {
       userGroupUserService.removeUser(id, userId);
       return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public Page<UserGroup> findAll(Pageable pageable) {
        return userGroupService.findPaginated(pageable);
    }
}
