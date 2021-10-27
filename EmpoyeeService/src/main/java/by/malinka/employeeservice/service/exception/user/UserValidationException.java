package by.malinka.employeeservice.service.exception.user;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class UserValidationException extends EmployeeServiceException {
    public UserValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
