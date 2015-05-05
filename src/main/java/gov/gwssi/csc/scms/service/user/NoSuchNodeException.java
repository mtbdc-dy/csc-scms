package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class NoSuchNodeException extends Throwable {
    public NoSuchNodeException() {
    }

    public NoSuchNodeException(String message) {
        super(message);
    }

    public NoSuchNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNodeException(Throwable cause) {
        super(cause);
    }

    public NoSuchNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
