package by.malinka.employeeservice.service.impl;

import by.malinka.employeeservice.entity.EmployeeGroup;
import by.malinka.employeeservice.persistence.EmployeeGroupRepository;
import by.malinka.employeeservice.service.EmployeeGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeGroupServiceImpl implements EmployeeGroupService {
    @Autowired
    private EmployeeGroupRepository employeeGroupRepository;

    @Override
    public EmployeeGroup addEmployeeGroup(EmployeeGroup employeeGroup) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public EmployeeGroup getByGroupName(String groupName) {
        return null;
    }

    @Override
    public EmployeeGroup editEmployeeGroup(EmployeeGroup employeeGroup) {
        return null;
    }

    @Override
    public List<EmployeeGroup> getAll() {
        return null;
    }
}
