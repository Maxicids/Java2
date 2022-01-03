package by.malinka.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cart.class)
public abstract class Cart_ {

	public static volatile SingularAttribute<Cart, Book> book;
	public static volatile SingularAttribute<Cart, Long> id;
	public static volatile SingularAttribute<Cart, User> customer;

	public static final String BOOK = "book";
	public static final String ID = "id";
	public static final String CUSTOMER = "customer";

}

