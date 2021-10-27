package by.malinka.employeeservice.service.exception.user;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class UserNotFoundException extends EmployeeServiceException {
    public UserNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
