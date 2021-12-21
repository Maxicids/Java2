package by.malinka.employeeservice.security;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsSecurity implements UserDetailsService {

    static final Logger log = LoggerFactory.getLogger(JwtUserDetailsSecurity.class);
    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user.get());
        log.info("LoadUserByUsername - user with email: " + email);
        return jwtUser;
    }
}
