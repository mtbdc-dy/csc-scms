package gov.gwssi.csc.scms.service.announcement;

public class NoSuchAnnouncementException extends Exception {
    public NoSuchAnnouncementException() {
    }

    public NoSuchAnnouncementException(String message) {
        super(message);
    }

    public NoSuchAnnouncementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAnnouncementException(Throwable cause) {
        super(cause);
    }
}
