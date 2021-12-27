package by.malinka.employeeservice.web.controller;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.MessageContext;
import by.malinka.employeeservice.model.MessageRequest;
import by.malinka.employeeservice.model.UserRequest;
import by.malinka.employeeservice.service.MessageContextService;
import by.malinka.employeeservice.service.MessageService;
import by.malinka.employeeservice.service.UserService;
import by.malinka.employeeservice.service.exception.message.MessageNotFoundException;
import by.malinka.employeeservice.service.exception.user.UserValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessagesController {
    static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final MessageService messageService;
    private final UserService userService;
    private final MessageContextService messageContextService;

    @Autowired
    public MessagesController(MessageService messageService, UserService userService, MessageContextService messageContextService) {
        this.messageService = messageService;
        this.userService = userService;
        this.messageContextService = messageContextService;
    }

    //        update
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("id") int id) {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @GetMapping("/findByRecipient")
    public  Page<Message> findByRecipient(
            @RequestBody @Valid UserRequest userRequest, Pageable pageable,
            BindingResult bindingResult) {
        log.info(userRequest.toString());
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return messageService.findByRecipientId(userService.getByEmail(userRequest.getEmail()), pageable);
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody @Valid MessageRequest messageRequest, BindingResult result) {
        if(result.hasErrors()) {
            throw new MessageNotFoundException("Invalid message");
        }
        log.info(messageRequest.toString());
        MessageContext messageContext = new MessageContext(messageRequest.getText());
        messageContextService.addMessageContext(messageContext);
        Message message = messageService.addMessage(
                new Message(
                        userService.getByEmail(messageRequest.getSenderEmail()),
                        userService.getByEmail(messageRequest.getRecipientEmail()),
                        messageContext
                )
        );
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam("id") int id) {
        messageService.delete(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<Message> findAll(Pageable pageable) {
        return messageService.findPaginated(pageable);
    }
}
