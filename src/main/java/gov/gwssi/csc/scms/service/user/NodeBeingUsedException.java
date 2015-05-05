package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class NodeBeingUsedException extends Throwable {
    public NodeBeingUsedException() {
    }

    public NodeBeingUsedException(String message) {
        super(message);
    }

    public NodeBeingUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeBeingUsedException(Throwable cause) {
        super(cause);
    }

    public NodeBeingUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
