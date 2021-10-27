package by.malinka.employeeservice.web.exception;

import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionServiceHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(UserAlreadyExistsException exception) {
        return exception.getExceptionMessage();
    }
}
