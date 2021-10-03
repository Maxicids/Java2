package by.malinka.employeeservice.service;

import by.malinka.employeeservice.entity.MessageContext;

import java.util.List;

public interface MessageContextService {
    MessageContext addMessage(MessageContext messageContext);
    void delete(int id);
    MessageContext editMessage(MessageContext messageContext);
    List<MessageContext> getAll();
}
