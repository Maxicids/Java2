package by.malinka.employeeservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Email(message = "Email must be email@domain.subdomain")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Pattern(regexp = "[a-zA-Z_0-9]{1,40}", message = "Password can contain ")
    private String password;

    @NotNull(message = "Name is required")
    @Pattern(regexp = "[a-zA-Z]{1,40}", message = "Name can contain ")
    private String name;

    @NotNull(message = "Surname is required")
    @Pattern(regexp = "[a-zA-Z]{1,40}", message = "Surname can contain ")
    private String surname;
}
