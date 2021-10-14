package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.MessageContext;
import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageContextService {
    MessageContext addMessageContext(MessageContext messageContext);
    void delete(int id);
    MessageContext editMessageContext(MessageContext messageContext);
    Page<MessageContext> findPaginated(Pageable pageable);
}
