package by.malinka.employeeservice.model;

import by.malinka.employeeservice.entity.User;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    //private int roleId;
    private String email;
    private String name;
    private String surname;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
    }
}
