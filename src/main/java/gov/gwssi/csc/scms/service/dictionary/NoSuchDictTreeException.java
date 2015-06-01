package gov.gwssi.csc.scms.service.dictionary;

/**
 * Created by WangZhenghua on 2015/5/31.
 */
public class NoSuchDictTreeException extends Exception{

    public NoSuchDictTreeException() {
    }

    public NoSuchDictTreeException(String message) {
        super(message);
    }

    public NoSuchDictTreeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDictTreeException(Throwable cause) {
        super(cause);
    }
}
