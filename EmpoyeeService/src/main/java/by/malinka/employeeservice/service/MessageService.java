package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface MessageService {
    Message addMessage(Message message);
    Optional<Message> findById(int id);
    Page<Message> findByRecipientId(User senderId, Pageable pageable);
    void delete(int id);
    Message editMessage(Message message);
    Page<Message> findPaginated(Pageable pageable);
}
