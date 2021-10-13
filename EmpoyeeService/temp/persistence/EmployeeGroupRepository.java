package by.malinka.employeeservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeGroupRepository extends PagingAndSortingRepository<EmployeeGroup, Integer> {

}
