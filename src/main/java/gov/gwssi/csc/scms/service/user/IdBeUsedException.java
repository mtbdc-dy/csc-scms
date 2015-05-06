package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 */
public class IdBeUsedException extends Throwable {
    public IdBeUsedException() {
    }

    public IdBeUsedException(String s) {
        super(s);
    }

    public IdBeUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdBeUsedException(Throwable cause) {
        super(cause);
    }

}
