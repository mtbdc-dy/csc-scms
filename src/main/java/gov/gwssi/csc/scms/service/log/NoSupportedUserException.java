package gov.gwssi.csc.scms.service.log;

/**
 * Created by Lei on 2015-06-11.
 */
public class NoSupportedUserException extends Exception {
    public NoSupportedUserException(String s) {
    }

    public NoSupportedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSupportedUserException(Throwable cause) {
        super(cause);
    }

    public NoSupportedUserException() {
    }
}
