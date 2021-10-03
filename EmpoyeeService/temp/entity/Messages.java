package by.malinka.employeeservice.entity;

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
@Table(name = "Messages")
public class Messages {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Id")
    private Role senderId;

    @ManyToOne
    @JoinColumn(name = "Id")
    private Role recipientId;

    @ManyToOne
    @JoinColumn(name = "Id")
    private EmployeeGroup recipientGroup;

    @OneToOne
    @JoinColumn(name = "Id")
    private MessageContext messageContextId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Messages messages = (Messages) o;
        return Objects.equals(id, messages.id);
    }
}
