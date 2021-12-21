package by.malinka.employeeservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JwtSecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsSecurity userDetailsSecurity;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtSecurityService(AuthenticationManager authenticationManager,
                              JwtUserDetailsSecurity userDetailsSecurity,
                              JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsSecurity = userDetailsSecurity;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtModel autologin(HttpServletRequest request, HttpServletResponse response,
                              String email, String password) {
        JwtUser userDetails = (JwtUser) userDetailsSecurity.loadUserByUsername(email);
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
}
