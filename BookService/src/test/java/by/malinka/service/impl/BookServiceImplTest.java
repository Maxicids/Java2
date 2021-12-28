package by.malinka.service.impl;

import by.malinka.domain.Book;
import by.malinka.repository.BookRepository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BookServiceImplTest {

    private BookServiceImpl bookService;
    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        book = new Book(1L, "title1", "a", "url", 1L, 1.11, "l", "g");
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void findAll() {
        when(bookRepository.findAll())
                .thenReturn(Collections.singletonList(book));
        Assertions.assertEquals(Collections.singletonList(book), bookService.findAll());
    }

    @Test
    void testFindAll() {
        when(bookRepository.findAll())
                .thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), bookService.findAll());
    }

    @Test
    void findById() {
        when(bookRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(book));
        Assertions.assertEquals(Optional.of(book), bookService.findById(any(Long.class)));
    }

    @Test
    void saveOrUpdate() {
        when(bookRepository.save(book))
                .thenReturn(book);
        Assertions.assertEquals(book, bookService.saveOrUpdate(book));
    }

    @Test
    void deleteById() throws JSONException {
        doNothing().when(bookRepository).deleteById(any(Long.class));
        Assertions.assertEquals(new JSONObject().put("message", "Book deleted successfully").toString(), bookService.deleteById(any(Long.class)));
    }
}