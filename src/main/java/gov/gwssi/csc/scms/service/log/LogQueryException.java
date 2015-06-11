package gov.gwssi.csc.scms.service.log;

/**
 * Created by Lei on 2015-06-11.
 */
public class LogQueryException extends Exception {

    public LogQueryException() {
    }

    public LogQueryException(String message) {
        super(message);
    }

    public LogQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogQueryException(Throwable cause) {
        super(cause);
    }
}
