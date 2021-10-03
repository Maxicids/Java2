package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends PagingAndSortingRepository<Messages, Integer> {
}
