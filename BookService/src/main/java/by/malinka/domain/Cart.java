package by.malinka.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Email(message = "Email must be email@domain.subdomain")
    @NotNull(message = "Email is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer", nullable = false)
    private User customer;

    @NotNull(message = "Book is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "book", nullable = false)
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart() {
    }

    public Cart( User customer, Book book) {
        this.customer = customer;
        this.book = book;
    }
}