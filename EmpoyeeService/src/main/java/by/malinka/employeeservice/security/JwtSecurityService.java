package by.malinka.employeeservice.security;

import by.malinka.employeeservice.service.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JwtSecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsSecurity userDetailsSecurity;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtSecurityService(AuthenticationManager authenticationManager,
                              JwtUserDetailsSecurity userDetailsSecurity,
                              JwtTokenProvider jwtTokenProvider,
                              PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsSecurity = userDetailsSecurity;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtModel autologin(HttpServletRequest request, HttpServletResponse response,
                              String email, String password) {
        JwtUser userDetails;
        try {
            userDetails = (JwtUser) userDetailsSecurity.loadUserByUsername(email);
        } catch(UsernameNotFoundException e) {
            return new JwtModel(false);
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = "Bearer_" + jwtTokenProvider.createToken(email, userDetails.getRole());
            response.addHeader("Authorization", token);
            return new JwtModel(userDetails.getId(), true, token, userDetails.getEmail(), userDetails.getRole());
        }
        return new JwtModel(false);
    }

    public String getHashedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
