package by.malinka.repository;

import by.malinka.domain.Cart;
import by.malinka.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {

    @Query("FROM Cart b WHERE b.customer = :searchText")
    Page<Cart> findAllCarts(Pageable pageable, @Param("searchText") User searchText);
}
