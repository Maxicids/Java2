package by.malinka.employeeservice.entity;

import by.malinka.employeeservice.model.UserRequest;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id", insertable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "RoleId", referencedColumnName = "Id")
    private Role roleId;

    @Column(name = "Email", length = 50, nullable = false, unique = true)
    private String email;


    @Column(name = "Name", length = 25, nullable = false)
    private String name;

    @Column(name = "Surname", length = 25, nullable = false)
    private String surname;

    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    public User(UserRequest userRequest) {
        this.name = userRequest.getName();
        this.surname = userRequest.getSurname();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
    }

}
