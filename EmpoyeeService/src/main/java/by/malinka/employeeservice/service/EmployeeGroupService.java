package by.malinka.empoyeeservice.service;

import by.malinka.empoyeeservice.entity.EmployeeGroup;
import java.util.List;

public interface EmployeeGroupService {
    EmployeeGroup addEmployeeGroup(EmployeeGroup employeeGroup);
    void delete(int id);
    EmployeeGroup getByGroupName(String groupName);
    EmployeeGroup editEmployeeGroup(EmployeeGroup employeeGroup);
    List<EmployeeGroup> getAll();
}
