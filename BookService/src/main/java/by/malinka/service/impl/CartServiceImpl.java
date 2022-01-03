package by.malinka.service.impl;

import by.malinka.domain.Book;
import by.malinka.domain.Cart;
import by.malinka.domain.User;
import by.malinka.repository.BookRepository;
import by.malinka.repository.CartRepository;
import by.malinka.repository.UserRepository;
import by.malinka.service.ICartService;
import by.malinka.service.IPageService;
import by.malinka.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartServiceImpl implements IService<Cart>, IPageService<Cart> , ICartService<Cart> {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Cart> findAll(Pageable pageable, String searchString) {
        return cartRepository.findAllCarts(pageable, userRepository.findByEmail(searchString));
    }

    @Override
    public Collection<Cart> findAll() {
        return (Collection<Cart>) cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart saveOrUpdate(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(User customer, Book book) {
        Cart cart = new Cart(customer, book);
        return cartRepository.save(cart);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            cartRepository.deleteById(id);
            jsonObject.put("message", "Cart item deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

}
