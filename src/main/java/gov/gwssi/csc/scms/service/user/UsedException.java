package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class UsedException extends Throwable {
    public UsedException() {
    }

    public UsedException(String message) {
        super(message);
    }

    public UsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedException(Throwable cause) {
        super(cause);
    }

    public UsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
