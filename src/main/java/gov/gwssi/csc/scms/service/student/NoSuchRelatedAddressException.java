package gov.gwssi.csc.scms.service.student;

public class NoSuchRelatedAddressException extends Exception {
    public NoSuchRelatedAddressException() {
    }

    public NoSuchRelatedAddressException(String message) {
        super(message);
    }

    public NoSuchRelatedAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRelatedAddressException(Throwable cause) {
        super(cause);
    }
}
