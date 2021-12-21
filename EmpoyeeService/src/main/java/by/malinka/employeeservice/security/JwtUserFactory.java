package by.malinka.employeeservice.security;

import by.malinka.employeeservice.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public final class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getPassword(),
                user.getRoleId(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoleId().getRoleName()))
        );
    }

}
