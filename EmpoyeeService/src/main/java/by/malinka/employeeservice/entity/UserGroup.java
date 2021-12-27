package by.malinka.employeeservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "UserGroups")
public class UserGroup {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="GroupName", length=20, nullable=false, unique=true)
    private String groupName;

    public UserGroup() {

    }

    public UserGroup(String groupName) {
        this.groupName = groupName;
    }
}
