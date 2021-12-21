package by.malinka.employeeservice.security;

import by.malinka.employeeservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {
    private int id;
    private boolean isLoggedIn;
    private String token;
    private String email;
    private Role role;

    public JwtModel(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}