package by.malinka.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private Long id;

	@Length(max = 255)
	@NotNull
	@Column(nullable = false)
	private String title;

	@Length(max = 255)
	@NotNull
	@Column(nullable = false)
	private String author;

	@Length(max = 255)
	@NotNull
	@Column(nullable = false)
	private String coverPhotoURL;

	@NotNull
	@Column(nullable = false)
	private Long isbnNumber;

	@Positive
	@NotNull
	@Column(nullable = false)
	private Double price;

	@Length(max = 255)
	@NotNull
	@Column(nullable = false)
	private String language;

	@Length(max = 255)
	@NotNull
	@Column(nullable = false)
	private String genre;

	public Book() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCoverPhotoURL() {
		return coverPhotoURL;
	}

	public void setCoverPhotoURL(String coverPhotoURL) {
		this.coverPhotoURL = coverPhotoURL;
	}

	public Long getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(Long isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Book(Long id, String title, String author, String coverPhotoURL, Long isbnNumber, Double price, String language, String genre) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.coverPhotoURL = coverPhotoURL;
		this.isbnNumber = isbnNumber;
		this.price = price;
		this.language = language;
		this.genre = genre;
	}
}
