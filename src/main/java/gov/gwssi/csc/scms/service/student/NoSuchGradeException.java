package gov.gwssi.csc.scms.service.student;

public class NoSuchGradeException extends Exception {
    public NoSuchGradeException() {
    }

    public NoSuchGradeException(String message) {
        super(message);
    }

    public NoSuchGradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGradeException(Throwable cause) {
        super(cause);
    }
}
