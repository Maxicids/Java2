package by.malinka.service.exception.cart;

import by.malinka.service.exception.BookServiceException;

public class CartNotFoundException extends BookServiceException {
    public CartNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
