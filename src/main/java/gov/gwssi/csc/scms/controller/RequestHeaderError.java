package gov.gwssi.csc.scms.controller;

/**
 * Created by Lei on 2015/6/5.
 */
public class RequestHeaderError extends Exception {
    public RequestHeaderError() {
    }

    public RequestHeaderError(String message) {
        super(message);
    }

    public RequestHeaderError(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHeaderError(Throwable cause) {
        super(cause);
    }
}
