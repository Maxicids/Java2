package by.malinka.repository;

import by.malinka.Application;
import by.malinka.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:testData.sql"})
class BookRepositoryTest {

    private final BookRepository bookRepository;

    private Book book = new Book(1L, "test1", "t1", "0", 0L, 1.11, "EN", "no");

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void findAllBooks() {
        System.out.println(bookRepository.findAll());
    }
}