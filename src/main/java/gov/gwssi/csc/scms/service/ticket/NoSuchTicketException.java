package gov.gwssi.csc.scms.service.ticket;

public class NoSuchTicketException extends Exception {
    public NoSuchTicketException() {
    }

    public NoSuchTicketException(String message) {
        super(message);
    }

    public NoSuchTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTicketException(Throwable cause) {
        super(cause);
    }
}
