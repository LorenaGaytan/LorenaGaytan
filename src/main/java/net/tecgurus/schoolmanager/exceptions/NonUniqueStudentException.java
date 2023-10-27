package net.tecgurus.schoolmanager.exceptions;

public class NonUniqueStudentException extends RuntimeException {

    public NonUniqueStudentException(String message) {
        super(message);
    }

    public NonUniqueStudentException(String message, Throwable cause) {
        super(message, cause);
    }
}
