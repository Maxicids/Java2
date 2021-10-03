package by.malinka.empoyeeservice.service;

import by.malinka.empoyeeservice.entity.Messages;
import java.util.List;

public interface MessagesService {
    Messages addMessages(Messages messages);
    void delete(int id);
    Messages getBySenderId(int senderId);
    Messages editMessages(Messages messages);
    List<Messages> getAll();
}
