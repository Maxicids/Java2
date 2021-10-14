package by.malinka.employeeservice.service.exception;

public class EmployeeServiceException extends RuntimeException {
    private final String exceptionMessage;

    public EmployeeServiceException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
