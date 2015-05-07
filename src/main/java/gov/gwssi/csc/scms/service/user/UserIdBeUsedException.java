package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class UserIdBeUsedException extends Throwable {
    public UserIdBeUsedException() {
    }

    public UserIdBeUsedException(String s) {
        super(s);
    }

    public UserIdBeUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdBeUsedException(Throwable cause) {
        super(cause);
    }

}
