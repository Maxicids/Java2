package by.malinka.employeeservice.security;

import by.malinka.employeeservice.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserSecurityDetails implements UserDetails {

    private final String password;
    private final ApplicationUserRole role;
    private final String email;
    private final int id;

    public UserSecurityDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = ApplicationUserRole.valueOf(user.getRoleId().getRoleName());
        this.id = user.getId();
    }

    public int getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ApplicationUserRole getRole() {
        return role;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
