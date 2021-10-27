package by.malinka.employeeservice.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {
    private Long id;
    private boolean isLoggedIn;
    private String token;
    private String username;
    private ApplicationUserRole role;

    public JwtModel(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
