package by.malinka.employeeservice.service;

import java.util.List;

public interface MessagesService {
    Messages addMessages(Messages messages);
    void delete(int id);
    Messages getBySenderId(int senderId);
    Messages editMessages(Messages messages);
    List<Messages> getAll();
}
