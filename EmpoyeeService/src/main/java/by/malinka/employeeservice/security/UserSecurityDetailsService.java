package by.malinka.employeeservice.security;

import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserSecurityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(this::entityToDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    private UserSecurityDetails entityToDetails(User userEntity) {
        return new UserSecurityDetails(userEntity);
    }
}
