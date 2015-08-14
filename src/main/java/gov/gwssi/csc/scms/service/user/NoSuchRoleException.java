package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class NoSuchRoleException extends Exception {
    public NoSuchRoleException() {
    }

    public NoSuchRoleException(String message) {
        super(message);
    }

    public NoSuchRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRoleException(Throwable cause) {
        super(cause);
    }

}
