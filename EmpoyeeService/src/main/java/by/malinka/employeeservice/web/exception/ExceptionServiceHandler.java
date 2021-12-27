package by.malinka.employeeservice.web.exception;

import by.malinka.employeeservice.service.exception.message.MessageNotValidException;
import by.malinka.employeeservice.service.exception.user.UserAlreadyExistsException;
import by.malinka.employeeservice.service.exception.user.UserNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionServiceHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(UserAlreadyExistsException exception) {
        return exception.getExceptionMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse userNotFoundException(UserNotFoundException exception) {
        return new ExceptionResponse("User with this id has not existed. Exception message: " + exception.getExceptionMessage(), 2);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionResponse badCredentialsException(BadCredentialsException exception) {
        return new ExceptionResponse("Bad credentials", 1);
    }

    @ExceptionHandler(MessageNotValidException.class)
    public ExceptionResponse messageNotValidException(MessageNotValidException exception) {
        return new ExceptionResponse("Message not valid", 1);
    }
}
