package by.malinka.employeeservice.service.exception.message;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class MessageNotFoundException extends EmployeeServiceException {
    public MessageNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
