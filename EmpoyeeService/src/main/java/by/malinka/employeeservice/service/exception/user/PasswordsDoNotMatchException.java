package by.malinka.employeeservice.service.exception.user;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class PasswordsDoNotMatchException extends EmployeeServiceException {
    public PasswordsDoNotMatchException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
