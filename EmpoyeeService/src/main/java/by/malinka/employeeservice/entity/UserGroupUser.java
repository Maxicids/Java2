package by.malinka.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "UserGroupUser")
@IdClass(UserGroupUser.class)
public class UserGroupUser implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User userId;

    @Id
    @PrimaryKeyJoinColumn
    @ManyToOne
    @JoinColumn(name = "UserGroupId", referencedColumnName = "Id")
    private UserGroup userGroupId;

    public UserGroupUser() {

    }
}
