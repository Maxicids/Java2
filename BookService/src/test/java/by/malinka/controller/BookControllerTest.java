package by.malinka.controller;

import by.malinka.controller.impl.BookControllerImpl;
import by.malinka.domain.Book;
import by.malinka.service.IPageService;
import by.malinka.service.IService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@WebMvcTest(BookControllerImpl.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IService<Book> bookService;

    @MockBean
    private IPageService<Book> bookPageService;


    @Test
    private void findAllTest() {

        Book book = new  Book(1L, "title1", "a", "url", 1L, 1.11, "l", "g");

        Mockito.when(bookService.findById(book.getId())).thenReturn(Optional.of(book));
        mockMvc.perform(get("book/" + book.getId());
    }
}
