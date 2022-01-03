package by.malinka.service.exception;

public class BookServiceException extends RuntimeException {
    private final String exceptionMessage;

    public BookServiceException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
