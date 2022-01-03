package by.malinka.controller.impl;

import by.malinka.controller.Controller;
import by.malinka.domain.Cart;
import by.malinka.service.ICartService;
import by.malinka.service.IPageService;
import by.malinka.service.exception.cart.CartNotFoundException;
import by.malinka.service.exception.cart.CartValidationException;
import by.malinka.service.exception.user.UserValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/carts")
@CrossOrigin(origins="http://localhost:3000")
public class CartControllerImpl implements Controller<Cart> {

    private final ICartService<Cart> cartService;
    private final IPageService<Cart> cartPageService;

    @Autowired
    public CartControllerImpl(ICartService<Cart> cartService, IPageService<Cart> cartPageService) {
        this.cartService = cartService;
        this.cartPageService = cartPageService;
    }

    @Override
    public ResponseEntity<Page<Cart>> findAll(Pageable pageable, String searchText) {
        return new ResponseEntity<>(cartPageService.findAll(pageable, searchText), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Cart>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(cartService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }

    @GetMapping("/with-email")
    public ResponseEntity<Page<Cart>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir,
                                              @RequestParam("email") String email) {
        return new ResponseEntity<>(cartPageService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                ),
                email
        ), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Page<Cart>> findAllWithEmail(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(cartService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> findById(Long id) {
        Optional<Cart> optionalCart = cartService.findById(id);
        if (optionalCart.isEmpty()) {
            throw new CartNotFoundException("Cart with id: " + id + " does not exists");
        }

        return new ResponseEntity<>(optionalCart.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> save(Cart cart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
            );
        }
        return new ResponseEntity<>(cartService.saveOrUpdate(cart), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody @Valid Cart cart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CartValidationException(
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
            );
        }
        return new ResponseEntity<>(cartService.addToCart(cart.getCustomer(), cart.getBook()),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cart> update(Cart cart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserValidationException(
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()
            );
        }
        return new ResponseEntity<>(cartService.saveOrUpdate(cart), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(cartService.deleteById(id), HttpStatus.OK);
    }
}
