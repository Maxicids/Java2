package by.malinka.employeeservice.service.exception.user;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class UserAlreadyExistsException extends EmployeeServiceException {
    public UserAlreadyExistsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
