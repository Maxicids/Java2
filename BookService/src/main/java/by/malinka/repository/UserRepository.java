package by.malinka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import by.malinka.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("FROM User WHERE email=:email")
	User findByEmail(@Param("email") String email);
}