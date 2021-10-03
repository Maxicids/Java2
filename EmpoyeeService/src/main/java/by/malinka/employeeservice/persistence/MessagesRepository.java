package by.malinka.empoyeeservice.repository;

import by.malinka.empoyeeservice.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Integer> {
}
