package by.malinka.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "MessageContexts")
public class MessageContext {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name="Text", length=20, nullable=false, unique=true)
    private String text;

    public MessageContext() {

    }
}
