package gov.gwssi.csc.scms.service.monitor;

/**
 * Created by Lei on 2015-06-11.
 */
public class MonitorQueryException extends Exception {

    public MonitorQueryException() {
    }

    public MonitorQueryException(String message) {
        super(message);
    }

    public MonitorQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MonitorQueryException(Throwable cause) {
        super(cause);
    }
}
