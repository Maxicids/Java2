package by.malinka.employeeservice.service.exception;

import org.hibernate.internal.EntityManagerMessageLogger;

public class EmployeeServiceException extends RuntimeException {
    private String exceptionMessage;

    public EmployeeServiceException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
