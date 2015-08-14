package gov.gwssi.csc.scms.service.warning;

public class NoSuchWarningException extends Exception {
    public NoSuchWarningException() {
    }

    public NoSuchWarningException(String message) {
        super(message);
    }

    public NoSuchWarningException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchWarningException(Throwable cause) {
        super(cause);
    }
}
