package by.malinka.service.exception.cart;

import by.malinka.service.exception.BookServiceException;

public class CartValidationException extends BookServiceException {
    public CartValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
