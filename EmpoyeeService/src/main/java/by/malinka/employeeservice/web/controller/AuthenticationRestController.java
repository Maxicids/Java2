package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.model.UserRequest;
import by.malinka.employeeservice.security.JwtModel;
import by.malinka.employeeservice.security.JwtSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/vi/auth")
public class AuthenticationRestController {

    private final JwtSecurityService securityService;

    @Autowired
    public AuthenticationRestController(JwtSecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody @Valid UserRequest userRequest,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadCredentialsException("Invalid data");
        }

        try {
            JwtModel model = securityService.autologin(request, response, userRequest.getEmail(), userRequest.getPassword());
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid data");
        }

    }
}
