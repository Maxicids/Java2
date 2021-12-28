package by.malinka.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, String> coverPhotoURL;
	public static volatile SingularAttribute<Book, Long> isbnNumber;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, Double> price;
	public static volatile SingularAttribute<Book, String> genre;
	public static volatile SingularAttribute<Book, String> language;
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> title;

	public static final String COVER_PHOTO_UR_L = "coverPhotoURL";
	public static final String ISBN_NUMBER = "isbnNumber";
	public static final String AUTHOR = "author";
	public static final String PRICE = "price";
	public static final String GENRE = "genre";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

