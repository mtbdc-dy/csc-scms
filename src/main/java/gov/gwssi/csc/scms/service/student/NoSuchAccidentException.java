package gov.gwssi.csc.scms.service.student;

public class NoSuchAccidentException extends Exception {
    public NoSuchAccidentException() {
    }

    public NoSuchAccidentException(String message) {
        super(message);
    }

    public NoSuchAccidentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAccidentException(Throwable cause) {
        super(cause);
    }
}
