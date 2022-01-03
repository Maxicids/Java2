package by.malinka.controller.advice;

import by.malinka.controller.Response;
import by.malinka.service.exception.book.BookNotFoundException;
import by.malinka.service.exception.book.BookValidationException;
import by.malinka.service.exception.cart.CartNotFoundException;
import by.malinka.service.exception.cart.CartValidationException;
import by.malinka.service.exception.user.UserValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionServiceHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserValidationException.class)
    public Response userNotValidException(UserValidationException exception) {
        return new Response(exception.getExceptionMessage(), 400);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartValidationException.class)
    public Response cartNotValidException(CartValidationException exception) {
        return new Response(exception.getExceptionMessage(), 400);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartNotFoundException.class)
    public Response cartNotFoundException(CartNotFoundException exception) {
        return new Response(exception.getExceptionMessage(), 400);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public Response bookNotFoundException(BookNotFoundException exception) {
        return new Response(exception.getExceptionMessage(), 400);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookValidationException.class)
    public Response bookValidationException(BookValidationException exception) {
        return new Response(exception.getExceptionMessage(), 400);
    }
}
