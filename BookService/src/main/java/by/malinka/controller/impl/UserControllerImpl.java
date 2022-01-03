package by.malinka.controller.impl;

import by.malinka.domain.Role;
import by.malinka.service.IRoleService;
import by.malinka.service.IUserService;
import by.malinka.service.exception.user.UserValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import by.malinka.config.JwtTokenProvider;
import by.malinka.domain.User;
import by.malinka.utils.ConstantUtils;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserControllerImpl {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final IRoleService<Role> roleService;
    private final IUserService<User> userService;

    @Autowired
    public UserControllerImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, IRoleService<Role> roleService, IUserService<User> userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Operation(summary = "Find Contacts by name", description = "Name search by %name% format", tags = { "contact" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))) })
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
            );
        }
        JSONObject jsonObject = new JSONObject();
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRole(roleService.findByName(ConstantUtils.USER.toString()));
            User savedUser = userService.saveOrUpdate(user);
            jsonObject.put("message", savedUser.getName() + " saved successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authenticate(@RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
            );
        }
        JSONObject jsonObject = new JSONObject();
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String email = user.getEmail();
                jsonObject.put("name", authentication.getName());
                jsonObject.put("authorities", authentication.getAuthorities());
                Role role = userService.findByEmail(email).getRole();
                jsonObject.put("token", tokenProvider.createToken(email, role));
                jsonObject.put("role", role.getName().toLowerCase(Locale.ROOT));
                return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
            }
        } catch (JSONException e) {
            try {
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}