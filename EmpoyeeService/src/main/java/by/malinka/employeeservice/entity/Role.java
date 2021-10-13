package by.malinka.employeeservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="RoleName", length=10, nullable=false, unique=true)
    private String roleName;

    public Role() {

    }
}
