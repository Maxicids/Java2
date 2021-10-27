package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.MessageContext;
import by.malinka.employeeservice.persistence.MessageContextRepository;
import by.malinka.employeeservice.service.MessageContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MessageContextServiceImpl implements MessageContextService {
    private final MessageContextRepository messageContextRepository;

    @Autowired
    public MessageContextServiceImpl(MessageContextRepository messageContextRepository) {
        this.messageContextRepository = messageContextRepository;
    }

    @Override
    public MessageContext addMessageContext(MessageContext messageContext) {
        return messageContextRepository.save(messageContext);
    }

    @Override
    public void delete(int id) {
        var optionalMessageContext = messageContextRepository.findById(id);
        optionalMessageContext.ifPresent(messageContextRepository::delete);
    }

    @Override
    public MessageContext editMessageContext(MessageContext messageContext) {
        return messageContextRepository.save(messageContext);
    }

    @Override
    public Page<MessageContext> findPaginated(Pageable pageable) {
        return messageContextRepository.findAll(pageable);
    }
}
