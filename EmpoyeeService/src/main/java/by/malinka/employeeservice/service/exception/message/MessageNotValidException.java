package by.malinka.employeeservice.service.exception.message;

import by.malinka.employeeservice.service.exception.EmployeeServiceException;

public class MessageNotValidException extends EmployeeServiceException {
    public MessageNotValidException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
