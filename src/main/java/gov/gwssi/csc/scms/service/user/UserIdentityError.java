package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/16.
 */
public class UserIdentityError extends Exception {
    public UserIdentityError() {
    }

    public UserIdentityError(String message) {
        super(message);
    }

    public UserIdentityError(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdentityError(Throwable cause) {
        super(cause);
    }
}
