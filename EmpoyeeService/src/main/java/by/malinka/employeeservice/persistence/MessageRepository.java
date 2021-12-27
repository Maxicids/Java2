package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.Message;
import by.malinka.employeeservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {
    Page<Message> findByRecipientId(User sender, Pageable pageable);
}
