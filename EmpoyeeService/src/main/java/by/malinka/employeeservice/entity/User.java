package by.malinka.empoyeeservice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Id")
    private Role roleId;

    @Column(name="Email", length=50, nullable=false, unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "Id")
    private EmployeeGroup employeeGroupId;

    @Column(name="Name", length=25, nullable=false)
    private String name;
    @Column(name="Surname", length=25, nullable=false)
    private String surname;
    @Column(name="MiddleName", length=25, nullable=false)
    private String middleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
}
