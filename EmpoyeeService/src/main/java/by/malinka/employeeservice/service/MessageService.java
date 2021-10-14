package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MessageService {
    Message addMessage(Message message);
    void delete(int id);
    Message editMessage(Message message);
    Page<Message> findPaginated(Pageable pageable);//TODO: pageable
}
