package gov.gwssi.csc.scms.service.user;

/**
 * Created by Lei on 2015/5/5.
 * 用户Id正在被使用一场
 */
public class UserIdBeingUsedException extends Throwable {
    public UserIdBeingUsedException() {
    }

    public UserIdBeingUsedException(String s) {
        super(s);
    }

    public UserIdBeingUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdBeingUsedException(Throwable cause) {
        super(cause);
    }

}
