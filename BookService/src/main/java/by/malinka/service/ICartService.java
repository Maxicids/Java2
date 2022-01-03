package by.malinka.service;

import by.malinka.domain.Book;
import by.malinka.domain.Cart;
import by.malinka.domain.User;

public interface ICartService<T> extends IService<T> {
    public T addToCart(User customer, Book book);
}
