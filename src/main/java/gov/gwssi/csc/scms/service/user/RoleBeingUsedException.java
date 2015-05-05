package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class RoleBeingUsedException extends Throwable {
    public RoleBeingUsedException() {
    }

    public RoleBeingUsedException(String message) {
        super(message);
    }

    public RoleBeingUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleBeingUsedException(Throwable cause) {
        super(cause);
    }

    public RoleBeingUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
