package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.User;
import by.malinka.employeeservice.persistence.MessageContextRepository;
import by.malinka.employeeservice.persistence.MessageRepository;
import by.malinka.employeeservice.service.MessageContextService;
import by.malinka.employeeservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(int id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        optionalMessage.ifPresent(messageRepository::delete);
    }

    @Override
    public Optional<Message> findById(int id) {
        return messageRepository.findById(id);
    }

    @Override
    public Page<Message> findByRecipientId(User senderId, Pageable pageable) {
        return messageRepository.findByRecipientId(senderId, pageable);
    }

    @Override
    public Message editMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> findPaginated(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
