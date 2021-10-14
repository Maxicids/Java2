package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.persistence.MessageContextRepository;
import by.malinka.employeeservice.persistence.MessageRepository;
import by.malinka.employeeservice.service.MessageContextService;
import by.malinka.employeeservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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
        var optionalMessage = messageRepository.findById(id);
        optionalMessage.ifPresent(messageRepository::delete);
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
