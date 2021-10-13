package by.malinka.employeeservice.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "Id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User() {

    }
}
