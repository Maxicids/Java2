package by.malinka.service.impl;


import by.malinka.domain.Book;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceIntegratedTest {


    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void findAll() {
        Assert.assertNotNull(bookService.findAll());
    }

    @Test
    public void findById() {
        Assert.assertNotNull(bookService.findById(1L));
    }

}
