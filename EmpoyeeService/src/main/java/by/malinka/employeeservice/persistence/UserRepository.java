package by.malinka.empoyeeservice.repository;

import by.malinka.empoyeeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
