package by.malinka.employeeservice.service.exception;

public class UserAlreadyExistsException extends EmployeeServiceException{
    public UserAlreadyExistsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
