package by.malinka.employeeservice.persistence;

import by.malinka.employeeservice.entity.MessageContext;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageContextRepository extends PagingAndSortingRepository<MessageContext, Integer> {
}
