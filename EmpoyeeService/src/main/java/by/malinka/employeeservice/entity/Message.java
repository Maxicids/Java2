package by.malinka.employeeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @Column(name = "Id", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "SenderId", referencedColumnName = "Id")
    private User senderId;

    @ManyToOne
    @JoinColumn(name = "RecipientId", referencedColumnName = "Id")
    private User recipientId;

    @ManyToOne
    @JoinColumn(name = "MessageId", referencedColumnName = "Id")
    private Message messageId;

    public Message() {

    }
}
