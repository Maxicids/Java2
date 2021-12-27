package by.malinka.employeeservice.model;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.MessageContext;
import by.malinka.employeeservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private String senderEmail;
    private String recipientEmail;
    private String text;

}
