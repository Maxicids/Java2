package by.malinka.service.exception.book;

import by.malinka.service.exception.BookServiceException;

public class BookValidationException extends BookServiceException {
    public BookValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
