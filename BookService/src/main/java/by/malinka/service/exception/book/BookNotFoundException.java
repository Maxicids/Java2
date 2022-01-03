package by.malinka.service.exception.book;

import by.malinka.service.exception.BookServiceException;

public class BookNotFoundException extends BookServiceException {
    public BookNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
