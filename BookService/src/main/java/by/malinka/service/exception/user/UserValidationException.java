package by.malinka.service.exception.user;


import by.malinka.service.exception.BookServiceException;

public class UserValidationException extends BookServiceException {
    public UserValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
