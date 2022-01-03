package by.malinka.controller.impl;

import java.util.*;

import by.malinka.service.exception.book.BookNotFoundException;
import by.malinka.service.exception.book.BookValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import by.malinka.domain.Book;
import by.malinka.controller.Controller;
import by.malinka.service.IPageService;
import by.malinka.service.IService;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins="http://localhost:3000")
public class BookControllerImpl implements Controller<Book> {

	private final IService<Book> bookService;
	private final IPageService<Book> bookPageService;

	@Autowired
	public BookControllerImpl(IService<Book> bookService, IPageService<Book> bookPageService) {
		this.bookService = bookService;
		this.bookPageService = bookPageService;
	}

	@Override
	public ResponseEntity<Page<Book>> findAll(Pageable pageable, String searchText) {
		return new ResponseEntity<>(bookPageService.findAll(pageable, searchText), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Book>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		return new ResponseEntity<>(bookService.findAll(
				PageRequest.of(
						pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
				)
		), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Book> findById(Long id) {
		Optional<Book> optionalBook = bookService.findById(id);
		if (optionalBook.isEmpty()) {
			throw new BookNotFoundException("Book with id: " + id + " does not exists");
		}
		return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Book> save(@Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BookValidationException(
					Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
			);
		}
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Book> update(@Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BookValidationException(
					Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
			);
		}
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
	}

	@GetMapping("/languages")
	public  ResponseEntity<Set<String>> findAllLanguages() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("French", "Portuguese", "English", "Russian", "Hindi", "Arabic", "Spanish", "Chinese")), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public  ResponseEntity<Set<String>> findAllGenres() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Technology", "Science", "History", "Fantasy", "Biography", "Horror", "Romance")), HttpStatus.OK);
    }
}
